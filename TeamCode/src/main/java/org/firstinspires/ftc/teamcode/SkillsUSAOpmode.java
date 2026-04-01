package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HARDWARE.ArmClawWristHardware;
import org.firstinspires.ftc.teamcode.HARDWARE.CameraHardware;
import org.firstinspires.ftc.teamcode.HARDWARE.TankDriveBasicFourMotor;

@TeleOp(name="SkillsUSA Main Drive", group="Linear Opmode")
public class SkillsUSAOpmode extends LinearOpMode {

    // Create an instance of your hardware class
    private final TankDriveBasicFourMotor tankDriveBasic = new TankDriveBasicFourMotor();
    private final ArmClawWristHardware armClawWristHardware = new ArmClawWristHardware();

    @Override
    public void runOpMode() {


        // 1. Initialize the hardware using the method in your TankDriveBasicFourMotor class
        CameraHardware camera = new CameraHardware(hardwareMap,"Webcam");
        tankDriveBasic.init(hardwareMap);
        armClawWristHardware.init(hardwareMap);

        telemetry.addData("Status", "Initialized - Ready to Drive");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            camera.init();
            double turn = gamepad1.right_stick_x;
            double drive = -gamepad1.left_stick_y;

            tankDriveBasic.tankDrive(drive, turn);
            ArmClawWristHardware robot = new ArmClawWristHardware();
            int armTarget = 0; // This variable tracks where the arm SHOULD be
            final int INCREMENT = 1; // How fast the arm moves with triggers

            robot.init(hardwareMap);
            waitForStart();

            while (opModeIsActive()) {
                // --- TRIGGER INCREMENTAL CONTROL ---
                if (gamepad1.left_trigger > 0.8) {
                    armTarget += INCREMENT;
                } else if (gamepad1.right_trigger > 0.8) {
                    armTarget -= INCREMENT;
                }


                // --- SAFETY LIMITS ---
                // Adjust these numbers so your arm doesn't hit the floor or flip too far back
                if (armTarget > 740) {
                    armTarget = 740;
                } else if (armTarget <= 60) {
                    armTarget = 60;
                }

                // --- SEND COMMAND TO MOTOR ---
                robot.setArmTarget(armTarget);

                if (gamepad1.right_bumper) {
                    armClawWristHardware.openClaw();
                } else if (gamepad1.left_bumper) {
                    armClawWristHardware.closeClaw();
                }

//            if(gamepad1.a){
//                armClawWristHardware.armHover();
//            } else if (gamepad1.b) {
//                armClawWristHardware.armStow();
//            }


                telemetry.addData("Drive Power", drive);
                telemetry.addData("Turn Power", turn);
                telemetry.addData("Target:", armTarget);
                telemetry.addData("Actual:", robot.ArmRotate.getCurrentPosition());
                telemetry.update();
            }
        }
    }
}