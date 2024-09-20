package Controller;

import Model.ImageModel;
import View.ImageView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageController {

    private ImageModel model;
    private ImageView view;

    public ImageController(ImageModel model, ImageView view) {
        this.model = model;
        this.view = view;

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        // "Open File" menu item
        JMenuItem openFileItem = new JMenuItem("Open File");
        openFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();  // Handle file opening
            }
        });
        fileMenu.add(openFileItem);

        // "Exit" menu item
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Exit the application
            }
        });
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        view.setJMenuBar(menuBar);
    }

    // Handle file opening logic
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("BMP Images", "bmp"));

        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage image = model.loadBMP(selectedFile);  // Load image using Model
                view.displayImage(image);  // Display image in View
            } catch (IOException e) {
                JOptionPane.showMessageDialog(view, "Error loading BMP file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
