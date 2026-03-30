package org.firstinspires.ftc.teamcode.HARDWARE;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmClawWristHardware {
    public DcMotor ArmRotate; // Removed static to avoid memory leaks between OpModes
    public Servo Claw;

    private static final double CLOSE_CLAW = 1.0;
    private static final double OPEN_CLAW = 0.0;

    public void init(HardwareMap hwMap) {
        ArmRotate = hwMap.get(DcMotor.class, "Arm");
        Claw = hwMap.get(Servo.class, "Claw");

        ArmRotate.setDirection(DcMotorSimple.Direction.FORWARD);

        // 1. Reset encoder so '0' is the starting position
        ArmRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // 2. Set the initial target to 0 before switching modes
        ArmRotate.setTargetPosition(0);
        ArmRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // 3. Keep the arm from falling
        ArmRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // 4. Set a base power. In RUN_TO_POSITION, this is the MAX speed allowed.
        ArmRotate.setPower(0.6);
    }

    // Helper method to change the target from your OpMode
    public void setArmTarget(int ticks) {
        ArmRotate.setTargetPosition(ticks);
    }

    public void closeClaw() { Claw.setPosition(CLOSE_CLAW); }
    public void openClaw() { Claw.setPosition(OPEN_CLAW); }
}




