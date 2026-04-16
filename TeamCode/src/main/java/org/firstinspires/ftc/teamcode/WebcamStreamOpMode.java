package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;

@TeleOp(name = "Webcam Live Stream", group = "Concept")
public class WebcamStreamOpMode extends LinearOpMode {

    private VisionPortal visionPortal;

    @Override
    public void runOpMode() {

        // 1. Initialize the VisionPortal
        // This will automatically start the camera stream
        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .build();

        telemetry.addData("Status", "Camera Initialized");
        telemetry.update();

        // Wait for the driver to press PLAY
        waitForStart();

        while (opModeIsActive()) {
            // The stream runs in the background automatically.
            // You can add your robot control logic here.

            telemetry.addData("Status", "Streaming live...");
            telemetry.addData("Stream State", visionPortal.getCameraState());
            telemetry.update();
        }

        // 2. Clean up: Close the portal when the OpMode stops
        visionPortal.close();
    }
}