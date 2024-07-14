package ru.avoronov.imagedetectionapp.files;

import com.google.common.io.Files;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class ImageFileSaver extends AbstractFileHandler {


    public void saveImage(BufferedImage image, String targetFilePath) {
        targetFilePath = addDefaultPath(targetFilePath);
        targetFilePath = addExtension(targetFilePath);
        targetFilePath = deduplicateFilename(targetFilePath);

        try {
            ImageIO.write(image, "png", new File(targetFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String deduplicateFilename(String targetFilePath) {
        String filePath = targetFilePath.substring(0, targetFilePath.lastIndexOf("/"));
        String fileName = Files.getNameWithoutExtension(targetFilePath);
        String fileExtension = Files.getFileExtension(targetFilePath);
        for (int i = 1; i < 100; i++) {
            if (new File(targetFilePath).exists()) {
                targetFilePath = filePath + File.separator + fileName + "_" + String.format("%02d",i) + "." + fileExtension;
            } else {
                break;
            }
        }
        return targetFilePath;
    }

        private String addExtension (String targetFilePath){
            if (!targetFilePath.endsWith(".png")) {
                targetFilePath = targetFilePath + ".png";
            }
            return targetFilePath;
        }

        private String addDefaultPath (String targetFilePath){
            if (!targetFilePath.contains(DEFAULT_IMAGE_PATH)) {
                targetFilePath = DEFAULT_IMAGE_PATH + targetFilePath;
            }
            return targetFilePath;
        }
    }
