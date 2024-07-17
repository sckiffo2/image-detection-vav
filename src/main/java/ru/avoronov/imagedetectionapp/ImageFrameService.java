package ru.avoronov.imagedetectionapp;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.image.BufferedImage;

@Service
public class ImageFrameService {
    // Создаём окно для просмотра изображения.

    public void createImageFrame(BufferedImage image) {
        JFrame myFrame = new JFrame("Window:");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.getContentPane().add(new JLabel(new ImageIcon(image)));
        myFrame.pack();
        myFrame.setVisible(true);
    }



}
