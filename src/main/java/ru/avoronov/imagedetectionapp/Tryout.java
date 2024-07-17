package ru.avoronov.imagedetectionapp;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;
import ru.avoronov.imagedetectionapp.files.ImageFileLoader;
import ru.avoronov.imagedetectionapp.files.ImageFileSaver;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Tryout {

    private static final int DEFAULT_SCREEN_NUM = 1;

    private final ScreenCaptureService screenCaptureService;
    private final ImageFileSaver imageFileSaver;
    private final ImageFileLoader imageFileLoader;
    private final ImageFrameService imageFrameService;

    @PostConstruct
    public void test() {
        BufferedImage screenshot = screenCaptureService.captureScreen(DEFAULT_SCREEN_NUM);
        BufferedImage imageToFind = imageFileLoader.loadImage("images/menu_main_menu.png");
//        BufferedImage imageToFind = imageFileLoader.loadInternalImage("bat_clock_red.png");


        Mat templ = Mat2BufferedImageConverter.toMat(imageToFind);
        Mat src = Mat2BufferedImageConverter.toMat(screenshot);

        Rect roi = new Rect(0, 0 , 400, 400);
        src = new Mat(src, roi);


        Mat result = new Mat();
        int result_cols = src.cols() - templ.cols() + 1;
        int result_rows = src.rows() - templ.rows() + 1;
        result.create(result_rows, result_cols, CvType.CV_32FC1);

        Imgproc.matchTemplate(src, templ, result, Imgproc.TM_CCOEFF_NORMED);

        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        imageFrameService.createImageFrame(Mat2BufferedImageConverter.toBufferedImage(result));
//        new MatchTemplateDemoRun().run(cropped, template);

    }

    public void findContours(Mat templ) {
        // / Do the Matching and Normalize
        Mat gray = new Mat(templ.rows(), templ.cols(), templ.type());
        Imgproc.cvtColor(templ, gray, Imgproc.COLOR_BGR2GRAY);
        Mat binary = new Mat(templ.rows(), templ.cols(), templ.type(), new Scalar(0));
        Imgproc.threshold(gray, binary, 100, 255, Imgproc.THRESH_BINARY_INV);
        //Finding Contours
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchey = new Mat();
        Imgproc.findContours(binary, contours, hierarchey, Imgproc.RETR_TREE,
                Imgproc.CHAIN_APPROX_SIMPLE);
        //Drawing the Contours
        Scalar color = new Scalar(0, 0, 255);
        Imgproc.drawContours(templ, contours, -1, color, 2, Imgproc.LINE_8,
                hierarchey, 2, new Point() ) ;
        HighGui.imshow("Drawing Contours", templ);
        HighGui.waitKey();
    }
}
