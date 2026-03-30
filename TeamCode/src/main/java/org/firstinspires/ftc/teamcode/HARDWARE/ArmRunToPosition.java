package org.firstinspires.ftc.teamcode.HARDWARE;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmRunToPosition {
    public static DcMotor ArmRotate;
    public void init(HardwareMap hwMap) {
        ArmRotate = hwMap.get(DcMotor.class, "Arm");
        ///  find if this is correct
        ArmRotate.setTargetPosition(0);
        ArmRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
// This keeps the arm from falling when you let go of the triggers
        ArmRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void armStow(){
        /// find real encoder value
        ArmRotate.setTargetPosition(0);
    }

    public void armHover(){
        /// find real encoder value
        ArmRotate.setTargetPosition(67);

    }
    public static void rotateArm(){ArmRotate.setPower(.5);}
    public static void unrotateArm(){ArmRotate.setPower(-.5);}

    public static void stopArm(){ArmRotate.setPower(0);}





}

