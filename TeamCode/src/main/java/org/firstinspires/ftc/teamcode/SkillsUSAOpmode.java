package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HARDWARE.ArmClawWristHardware;
import org.firstinspires.ftc.teamcode.HARDWARE.TankDriveBasicFourMotor;

@TeleOp(name="SkillsUSA Main Drive", group="Linear Opmode")
public class SkillsUSAOpmode extends LinearOpMode {

    // Create an instance of your hardware class
    private final TankDriveBasicFourMotor tankDriveBasic = new TankDriveBasicFourMotor();
    private final ArmClawWristHardware armClawWristHardware = new ArmClawWristHardware();

    @Override
    public void runOpMode() {


        // 1. Initialize the hardware using the method in your TankDriveBasicFourMotor class
        tankDriveBasic.init(hardwareMap);
        armClawWristHardware.init(hardwareMap);

        telemetry.addData("Status", "Initialized - Ready to Drive");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            double turn = gamepad1.right_stick_x;
            double drive = -gamepad1.left_stick_y;
            tankDriveBasic.tankDrive(drive, turn);

            if(gamepad1.left_trigger > .1){
                ArmClawWristHardware.rotateArm();
            } else if (gamepad1.right_trigger >.1) ArmClawWristHardware.unrotateArm();
            else ArmClawWristHardware.stopArm();

            if (gamepad1.right_bumper){
                armClawWristHardware.openClaw();
            }else if (gamepad1.left_bumper){
                armClawWristHardware.closeClaw();
            }

//            if(gamepad1.a){
//                armClawWristHardware.armHover();
//            } else if (gamepad1.b) {
//                armClawWristHardware.armStow();
//            }


            telemetry.addData("Drive Power", drive);
            telemetry.addData("Turn Power", turn);
            telemetry.addData("Arm Encoder", ArmClawWristHardware.ArmRotate.getCurrentPosition());
            telemetry.addData("Claw Encoder", ArmClawWristHardware.Claw.getPosition());
            telemetry.update();
        }
    }
}
