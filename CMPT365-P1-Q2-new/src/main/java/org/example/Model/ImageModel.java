package org.example.Model;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageModel {
    public BufferedImage loadBMP(File bmpFile) throws IOException {
        // Use Apache Commons Imaging to read the BMP file
        BufferedImage image;
        try {
            image = Imaging.getBufferedImage(bmpFile);
        } catch (ImageReadException e) {
            throw new RuntimeException(e);
        }
        if (image == null) {
            throw new IOException("Invalid BMP file.");
        }
        return image;
    }
}
