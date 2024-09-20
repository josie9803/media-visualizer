package Model;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageModel {

    // Method to load BMP file and return BufferedImage
    public BufferedImage loadBMP(File bmpFile) throws IOException {
        // Same logic as the readBMP function from the original code
        // Read BMP file and return a BufferedImage
        try (FileInputStream fis = new FileInputStream(bmpFile)) {
            byte[] header = new byte[54];
            fis.read(header, 0, 54);  // BMP header

            int width = ((header[21] & 0xFF) << 24) | ((header[20] & 0xFF) << 16) | ((header[19] & 0xFF) << 8) | (header[18] & 0xFF);
            int height = ((header[25] & 0xFF) << 24) | ((header[24] & 0xFF) << 16) | ((header[23] & 0xFF) << 8) | (header[22] & 0xFF);
            int bitsPerPixel = ((header[29] & 0xFF) << 8) | (header[28] & 0xFF);

            if (bitsPerPixel != 24) {
                throw new IOException("Only 24-bit BMP images are supported.");
            }

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            int padding = (4 - (width * 3) % 4) % 4;  // Row padding
            byte[] pixelData = new byte[(width * 3 + padding) * height];

            fis.read(pixelData);  // Read pixel data

            int index = 0;
            for (int y = height - 1; y >= 0; y--) {  // BMP is upside down
                for (int x = 0; x < width; x++) {
                    int blue = pixelData[index++] & 0xFF;
                    int green = pixelData[index++] & 0xFF;
                    int red = pixelData[index++] & 0xFF;
                    int rgb = (red << 16) | (green << 8) | blue;
                    image.setRGB(x, y, rgb);
                }
                index += padding;
            }

            return image;
        }
    }
}
