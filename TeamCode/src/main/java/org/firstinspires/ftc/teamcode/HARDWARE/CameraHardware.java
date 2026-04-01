package org.firstinspires.ftc.teamcode.HARDWARE;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import android.util.Size;

public class CameraHardware {
    public VisionPortal visionPortal;
    private WebcamName webcamName;

    // Constructor
    public CameraHardware(HardwareMap hwMap, String webcamDeviceName) {
        webcamName = hwMap.get(WebcamName.class, webcamDeviceName);
    }

    public void init() {
        visionPortal = new VisionPortal.Builder()
                .setCamera(webcamName)
                .setCameraResolution(new Size(640, 480)) // Standard resolution for low lag
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .setAutoStopLiveView(false) // Keeps the HDMI output active
                .build();
    }

    public void stopStreaming() {
        if (visionPortal != null) {
            visionPortal.stopStreaming();
        }
    }

    public void resumeStreaming() {
        if (visionPortal != null) {
            visionPortal.resumeStreaming();
        }
    }

    public void close() {
        if (visionPortal != null) {
            visionPortal.close();
        }
    }
}
