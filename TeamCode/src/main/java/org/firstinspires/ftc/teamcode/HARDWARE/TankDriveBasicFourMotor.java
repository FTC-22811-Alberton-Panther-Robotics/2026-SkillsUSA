package org.firstinspires.ftc.teamcode.HARDWARE;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TankDriveBasicFourMotor {
    public DcMotor rightDrive;
    public DcMotor leftDrive;
    double speedMultiplier = 1.0;

    HardwareMap hwMap;

    public void init(HardwareMap hwMap) {

        rightDrive = hwMap.get(DcMotor.class, "rightDrive");

        leftDrive = hwMap.get(DcMotor.class, "leftDrive");

        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void tankDrive(double drivePower, double turnPower) {

        double leftPower = (drivePower + turnPower) * speedMultiplier;
        double rightPower = (drivePower - turnPower)* speedMultiplier;
        double max = Math.max(Math.abs(leftPower), Math.abs(rightPower));

        if (max > 1.0) {
            leftPower /= max;
            rightPower /= max;

        }
        rightDrive.setPower(rightPower);
        leftDrive.setPower(leftPower);
    }

    // Set the slow mode scale (e.g., 0.5 for half speed)
    public void setSlowMode(double scale) {
        this.speedMultiplier = scale;
    }

    // Method to drive with the multiplier applied
    public void drive(double left, double right) {
        leftDrive.setPower(left * speedMultiplier);
        rightDrive.setPower(right * speedMultiplier);
    }
}
