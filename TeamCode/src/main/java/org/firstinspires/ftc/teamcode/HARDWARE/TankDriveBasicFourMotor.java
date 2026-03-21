package org.firstinspires.ftc.teamcode.HARDWARE;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TankDriveBasicFourMotor {
    public DcMotor rightDrive;
    public DcMotor leftDrive;

    HardwareMap hwMap;

    public void init(HardwareMap hwMap) {

        rightDrive = hwMap.get(DcMotor.class, "rightDrive");

        leftDrive = hwMap.get(DcMotor.class, "leftDrive");

        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void tankDrive(double drivePower, double turnPower) {

        double leftPower = drivePower + turnPower;
        double rightPower = drivePower - turnPower;
        double max = Math.max(Math.abs(leftPower), Math.abs(rightPower));

        if (max > 1.0) {
            leftPower /= max;
            rightPower /= max;

        }
        rightDrive.setPower(rightPower);
        leftDrive.setPower(leftPower);
    }
}
