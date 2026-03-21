package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HARDWARE.ArmandClaswndwristHardware;
import org.firstinspires.ftc.teamcode.HARDWARE.TankDriveBasicFourMotor;

@TeleOp(name="SkillsUSA Main Drive", group="Linear Opmode")
public class SkillsUSAOpmode extends LinearOpMode {

    // Create an instance of your hardware class
    private final TankDriveBasicFourMotor tankDriveBasic = new TankDriveBasicFourMotor();
    private final ArmandClaswndwristHardware armandClaswndwristHardware = new ArmandClaswndwristHardware();

    @Override
    public void runOpMode() {


        // 1. Initialize the hardware using the method in your TankDriveBasicFourMotor class
        tankDriveBasic.init(hardwareMap);
        armandClaswndwristHardware.init(hardwareMap);

        telemetry.addData("Status", "Initialized - Ready to Drive");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            double turn = gamepad1.left_stick_x;
            double drive = -gamepad1.left_stick_y;
            tankDriveBasic.tankDrive(drive, turn);

            if(gamepad1.right_trigger > .1){
                ArmandClaswndwristHardware.rotateArm();
            } else if (gamepad1.left_trigger >.1) ArmandClaswndwristHardware.unrotateArm();

            if (gamepad1.right_bumper){
                armandClaswndwristHardware.openClaw();
            }else if (gamepad1.left_bumper){
                armandClaswndwristHardware.closeClaw();
            }

            if(gamepad1.a){
                armandClaswndwristHardware.ARMHOVER();
            } else if (gamepad1.b) {
                armandClaswndwristHardware.ARMSTOW();
            }


            telemetry.addData("Drive Power", drive);
            telemetry.addData("Turn Power", turn);
            telemetry.addData("Arm Encoder", armandClaswndwristHardware.currentPosition);
            telemetry.addData("claw Encoder", armandClaswndwristHardware.currentClawPosition);
            telemetry.update();
        }
    }
}
