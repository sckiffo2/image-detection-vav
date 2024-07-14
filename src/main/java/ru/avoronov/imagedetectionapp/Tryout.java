package ru.avoronov.imagedetectionapp;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.avoronov.imagedetectionapp.files.ImageFileLoader;
import ru.avoronov.imagedetectionapp.files.ImageFileSaver;

import java.awt.image.BufferedImage;

@Component
@RequiredArgsConstructor
public class Tryout {

    private static final int DEFAULT_SCREEN_NUM = 1;

    private final ScreenCaptureService screenCaptureService;
    private final ImageFileSaver imageFileSaver;
    private final ImageFileLoader imageFileLoader;

    @PostConstruct
    public void test() {
        BufferedImage screenshot = screenCaptureService.captureScreen(DEFAULT_SCREEN_NUM);
        BufferedImage imageToFind = imageFileLoader.loadInternalImage("test2.jpg");

//        imageFileSaver.saveImage(bufferedImage, "screenshot103");
    }
}
