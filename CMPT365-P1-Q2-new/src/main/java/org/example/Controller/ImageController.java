package org.example.Controller;
import org.example.Model.ImageModel;
import org.example.View.ImageView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageController {

    private final ImageModel model;
    private final ImageView view;

    public ImageController(ImageModel model, ImageView view) {
        this.model = model;
        this.view = view;

        view.addOpenFileListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        view.addExitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    private void openFile() {
        File selectedFile = view.showOpenFileDialog();
        if (selectedFile != null) {
            try {
                BufferedImage image = model.loadBMP(selectedFile);
                view.updateImage(image);
            } catch (IOException e) {
                view.showError("Error loading BMP file: " + e.getMessage());
            }
        }
    }
}
