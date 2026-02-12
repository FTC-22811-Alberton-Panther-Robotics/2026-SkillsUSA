package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="SkillsUSA Main Drive", group="Linear Opmode")
public class SkillsUSAOpmode extends LinearOpMode {

    // Create an instance of your hardware class
    private TankDriveBasic tankDriveBasic = new TankDriveBasic();

    @Override
    public void runOpMode() {
        // 1. Initialize the hardware using the method in your TankDriveBasic class
        tankDriveBasic.init(hardwareMap);

        telemetry.addData("Status", "Initialized - Ready to Drive");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {

            double drive = -gamepad1.left_stick_y;

            double turn = gamepad1.right_stick_x;

]            tankDriveBasic.TANKDRIVE(drive, turn);

            telemetry.addData("Drive Power", drive);
            telemetry.addData("Turn Power", turn);
            telemetry.update();
        }
    }
}
