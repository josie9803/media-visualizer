package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageView extends JFrame {

    private JLabel imageLabel;

    public ImageView() {
        setTitle("BMP Image Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        add(new JScrollPane(imageLabel), BorderLayout.CENTER);
    }

    // Method to update the displayed image
    public void displayImage(BufferedImage image) {
        imageLabel.setIcon(new ImageIcon(image));
        pack();  // Resize the window to fit the new image
    }
}
