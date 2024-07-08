package ru.avoronov.imagedetectionapp;

import jakarta.annotation.PostConstruct;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.stereotype.Component;

@Component
public class Tryout {

    @PostConstruct
    public void test() {

        Mat imageMatrix = loadImage("C:\\Users\\a.voronov\\IdeaProjects\\ImageDetection\\src\\main\\resources\\Screenshot_47.jpg");
        saveImage(imageMatrix, "C:\\Users\\a.voronov\\IdeaProjects\\ImageDetection\\src\\main\\resources\\test2.jpg");
    }

    public Mat loadImage(String imagePath) {
        return Imgcodecs.imread(imagePath);
    }

    public void saveImage(Mat imageMatrix, String targetPath) {
        Imgcodecs.imwrite(targetPath, imageMatrix);
    }
}
