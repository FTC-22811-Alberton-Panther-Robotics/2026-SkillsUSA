package org.firstinspires.ftc.teamcode.HARDWARE;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmRunToPosition {
    public static DcMotor ArmRotate;
    //    public static Servo Wrist;
    public static Servo Claw;
    HardwareMap hwMap;
    private static final double CLOSE_CLAW = 1;
    private static final double OPEN_CLAW = 0;
    //   private static final double WRIST_DOWN = 0;
    // private static final double WRIST_UP = 1;

    public void init(HardwareMap hwMap) {
        ArmRotate = hwMap.get(DcMotor.class, "Arm");
        //Wrist= hwMap.get(Servo.class, "WristRotate");
        Claw = hwMap.get(Servo.class, "Claw");

        ///  find if this is correct
        ArmRotate.setDirection(DcMotorSimple.Direction.FORWARD);
        ArmRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmRotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void closeClaw(){
        Claw.setPosition(CLOSE_CLAW);
    }
    public void openClaw(){
        Claw.setPosition(OPEN_CLAW);
    }

    public void armStow(){
        /// find real encoder value
        ArmRotate.setTargetPosition(0);
    }

    public void armHover(){
        /// find real encoder value
        ArmRotate.setTargetPosition(67);

    }
     qwqwasasas
    //    public static void wristDown(){Wrist.setPosition(WRIST_DOWN);}
//    public static void wristUp(){Wrist.setPosition(WRIST_UP);}
    public static void rotateArm(){ArmRotate.setPower(.5);}
    public static void unrotateArm(){ArmRotate.setPower(-.5);}

    public static void stopArm(){ArmRotate.setPower(0);}





}

