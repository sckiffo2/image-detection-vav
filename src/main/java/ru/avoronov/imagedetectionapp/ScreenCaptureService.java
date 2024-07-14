package ru.avoronov.imagedetectionapp;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class ScreenCaptureService {

    public BufferedImage captureScreen(int screenNumber) {
        GraphicsDevice[] screenDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        var screenRect = screenDevices[screenNumber].getDefaultConfiguration().getBounds();
        try {
            return new Robot().createScreenCapture(screenRect);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}
