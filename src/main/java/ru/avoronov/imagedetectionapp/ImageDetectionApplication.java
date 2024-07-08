package ru.avoronov.imagedetectionapp;

import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageDetectionApplication {

    public static void main(String[] args) {
        OpenCV.loadShared();
        SpringApplication.run(ImageDetectionApplication.class, args);
    }

}
