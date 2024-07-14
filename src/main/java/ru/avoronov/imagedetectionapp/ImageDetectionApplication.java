package ru.avoronov.imagedetectionapp;

import nu.pattern.OpenCV;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ImageDetectionApplication {

    public static void main(String[] args) {
        OpenCV.loadShared();

        SpringApplicationBuilder builder = new SpringApplicationBuilder(ImageDetectionApplication.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
    }

}
