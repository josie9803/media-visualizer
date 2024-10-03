package org.example;
import org.example.Controller.ImageController;
import org.example.Model.ImageModel;
import org.example.View.ImageView;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageModel model = new ImageModel();
            ImageView view = new ImageView();
            new ImageController(model, view);

            view.setVisible(true);
        });
    }
}
