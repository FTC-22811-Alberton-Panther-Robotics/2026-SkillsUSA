package org.firstinspires.ftc.teamcode.HARDWARE;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmandClaswndwristHardware {
    public static DcMotor ArmRotate;
    public static Servo Wrist;
    public static Servo Claw;
    HardwareMap hwMap;
    public int currentPosition = ArmRotate.getCurrentPosition();
    public double currentClawPosition = Claw.getPosition();


    private static final double CLOSECLAW = 1;
    private static final double OPENCLAW = 0;
    private static final double WRISTDOWN = 0;
    private static final double WRISTUP = 1;


    public void init(HardwareMap hwMap) {
        ArmRotate = hwMap.get(DcMotor.class, "ArmRotate");
        Wrist= hwMap.get(Servo.class, "WriteRotate");
        Claw = hwMap.get(Servo.class, "Claw");

        ///  find if this is correct
        ArmRotate.setDirection(DcMotorSimple.Direction.FORWARD);
        ArmRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmRotate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }

    public void closeClaw(){
        Claw.setPosition(CLOSECLAW);
    }
    public void openClaw(){
         Claw.setPosition(OPENCLAW);
    }

    public void ARMSTOW(){
        /// find real encoder value
        ArmRotate.setTargetPosition(0);
    }

    public void ARMHOVER(){
        /// find real encoder value
        ArmRotate.setTargetPosition(67);
    }
    public static void wristdown(){Wrist.setPosition(WRISTDOWN);}
    public static void wristup(){Wrist.setPosition(WRISTUP);}
    public static void rotateArm(){ArmRotate.setPower(.5);}
    public static void unrotateArm(){ArmRotate.setPower(-.5);}





}

