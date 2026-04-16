package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.HARDWARE.ArmClawWristHardware;
import org.firstinspires.ftc.teamcode.HARDWARE.CameraHardware;
import org.firstinspires.ftc.teamcode.HARDWARE.TankDriveBasicFourMotor;
import org.firstinspires.ftc.vision.VisionPortal;

@TeleOp(name="SkillsUSA Main Drive", group="Linear Opmode")
public class SkillsUSAOpmode extends LinearOpMode {

    private static boolean USE_WEBCAM;

    private final TankDriveBasicFourMotor tankDriveBasic = new TankDriveBasicFourMotor();
    private final ArmClawWristHardware armClawWristHardware = new ArmClawWristHardware();

    // 1. Declare armTarget OUTSIDE the loop so it remembers its position
    int armTarget = 0;
    final int INCREMENT = 5; // Increased from 1 (1 is very slow)


    @Override
    public void runOpMode() {
        // Initialize everything ONCE
        tankDriveBasic.init(hardwareMap);
        armClawWristHardware.init(hardwareMap);


        telemetry.addData("Status", "Initialized - Ready");
        telemetry.update();

        waitForStart();

        // SINGLE Loop for everything
        while (opModeIsActive()) {
            // --- DRIVE CONTROL ---
            double turn = gamepad1.left_stick_y;
            double drive = -gamepad1.right_stick_x;
            tankDriveBasic.tankDrive(drive, turn);

            // --- ARM INCREMENTAL CONTROL ---
            if (gamepad1.left_trigger > 0.3) { // Lowered threshold from 0.8 for better feel
                armTarget += INCREMENT;
            } else if (gamepad1.right_trigger > 0.3) {
                armTarget -= INCREMENT;
            }

            // --- ARM PRESETS (Optional - Uncomment to use) ---
            if (gamepad1.a) armTarget = 60;  // Hover
            if (gamepad1.b) armTarget = 0;   // Stow

            // --- SAFETY LIMITS ---
            if (armTarget > 740) armTarget = 740;
            if (armTarget < 0) armTarget = 0;

            // --- APPLY ARM TARGET ---
            armClawWristHardware.setArmTarget(armTarget);

            // --- CLAW CONTROL ---
            if (gamepad1.right_bumper) {
                armClawWristHardware.openClaw();
            } else if (gamepad1.left_bumper) {
                armClawWristHardware.closeClaw();

// Inside your OpMode loop
                if (gamepad1.dpad_up) {
                    tankDriveBasic.setSlowMode(0.2); // Activate Slow Mode (40% power)
                } else if (gamepad1.dpad_down) {
                    tankDriveBasic.setSlowMode(1.0); // Deactivate (Full power)
                }


            }

            // --- TELEMETRY ---
            telemetry.addData("Drive", "Drive: %.2f, Turn: %.2f", drive, turn);

            telemetry.addData("Arm Target", armTarget);
            telemetry.addData("Arm Actual", armClawWristHardware.ArmRotate.getCurrentPosition());
            telemetry.update();
        }
    }
}