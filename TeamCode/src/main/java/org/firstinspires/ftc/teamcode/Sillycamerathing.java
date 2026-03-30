package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

@TeleOp(name="GoPro HERO 5 Feed", group="Vision")
public class Sillycamerathing extends LinearOpMode {

    OpenCvCamera gopro;

    @Override
    public void runOpMode() {
        // Retrieve the camera monitor ID so we can see the feed on the Robot Controller phone/Control Hub HDMI
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        // The Capture Card will show up as "Webcam 1" in your hardware configuration
        gopro = OpenCvCameraFactory.getInstance().createWebcam(
                hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        // Set the pipeline (processing instructions)
        gopro.setPipeline(new SimplePassThroughPipeline());

        // Open the camera asynchronously
        gopro.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                // HERO 5 via HDMI usually outputs 1080p, but we downsample
                // to 640x480 for better performance on the Driver Hub.
                gopro.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                telemetry.addData("Error", "GoPro could not open. Code: " + errorCode);
                telemetry.update();
            }
        });

        telemetry.addLine("GoPro Initialized. Press Start to begin.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Your driving logic goes here
            telemetry.addData("Status", "Streaming GoPro Feed");
            telemetry.update();
        }
    }

    // A basic pipeline that just sends the image to the screen without changes
    class SimplePassThroughPipeline extends OpenCvPipeline {
        @Override
        public Mat processFrame(Mat input) {
            return input;
        }
    }
}