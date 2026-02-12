package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TankDriveBasic {
public DcMotor rightFrontDrive;
public DcMotor rightBackDrive;
public DcMotor leftFrontDrive;
public DcMotor leftBackDrive;

HardwareMap hwMap;

public void init(HardwareMap hwMap){

    rightFrontDrive = hwMap.get(DcMotor.class, "rightFrontDrive");
    rightBackDrive = hwMap.get(DcMotor.class, "rightBackDrive");
    leftBackDrive = hwMap.get(DcMotor.class, "leftBackDrive");
    leftFrontDrive = hwMap.get(DcMotor.class, "leftFrontDrive");


    /// TODO: figure out what is actually reversed and vise versa
    rightBackDrive.setDirection(DcMotorSimple.Direction.FORWARD);
    rightFrontDrive.setDirection(DcMotorSimple.Direction.FORWARD);
    leftFrontDrive.setDirection(DcMotorSimple.Direction.REVERSE);
    leftFrontDrive.setDirection(DcMotorSimple.Direction.REVERSE);


}

public void TANKDRIVE(double drivePower, double turnPower){

        ///  TODO: CHANGE IF WORNGE
        double leftpower = drivePower + turnPower;
        double rightpower = drivePower - turnPower;

        rightBackDrive.setPower(rightpower);
        rightFrontDrive.setPower(rightpower);
        leftBackDrive.setPower(leftpower);
        leftFrontDrive.setPower(leftpower);




}
}
