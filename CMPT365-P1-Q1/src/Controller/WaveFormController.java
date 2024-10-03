package Controller;
import Model.WaveFormModel;
import View.WaveFormView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class WaveFormController {
    private final WaveFormView view;

    public WaveFormController(WaveFormView view) {
        this.view = view;
        view.show();
    }

    public void init() {
        view.addOpenFileButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFileOpen();
            }
        });
    }
    private void handleFileOpen() {
        File selectedFile = view.showOpenFileDialog();
        if (selectedFile != null) {
            try {
                // Create WaveFormModel instance, which reads the WAV file
                WaveFormModel waveFormModel = new WaveFormModel(selectedFile);
                int[][] samples = waveFormModel.getSamples();
                AudioFormat format = waveFormModel.getAudioFormat();

                if (samples != null && format != null) {
                    int sampleRate = (int) format.getSampleRate();
                    int totalSamples = samples[0].length;

                    // Update the view with the new waveform data
                    view.updateWaveformPanel(samples[0], samples[1], sampleRate, totalSamples);
                }
            } catch (Exception e) {
                System.err.println("Error reading WAV file: " + e.getMessage());
            }
        }
    }

//    private void handleFileOpen() {
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("WAV Sound Files", "wav"));
//
//        int result = fileChooser.showOpenDialog(view.getMainPanel());
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = fileChooser.getSelectedFile();
//            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
//
//            try {
//                // Create WaveFormModel instance, which reads the WAV file
//                WaveFormModel = new WaveFormModel(selectedFile);
//                int[][] samples = waveFormModel.getSamples();
//                AudioFormat format = waveFormModel.getAudioFormat();
//
//                if (samples != null && format != null) {
//                    int sampleRate = (int) format.getSampleRate();
//                    int totalSamples = samples[0].length;
//
//                    // Update the view with the new waveform data
//                    view.updateWaveformPanel(samples[0], samples[1], sampleRate, totalSamples);
//                }
//            } catch (Exception e) {
//                System.err.println("Error reading WAV file: " + e.getMessage());
//            }
//        } else {
//            System.out.println("File selection was canceled.");
//        }
//    }

}
