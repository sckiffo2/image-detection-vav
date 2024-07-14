package ru.avoronov.imagedetectionapp.files;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class ImageFileLoader extends AbstractFileHandler {

    public BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage loadInternalImage(final String internalImagePath) {
        String finalPath = "/images/" + internalImagePath;
        try {
            return ImageIO.read(getClass().getResource(finalPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
