import Controller.ImageController;
import Model.ImageModel;
import View.ImageView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageModel model = new ImageModel();  // Create the Model
            ImageView view = new ImageView();     // Create the View
            new ImageController(model, view);     // Create the Controller

            view.setVisible(true);  // Show the View
        });
    }
}
