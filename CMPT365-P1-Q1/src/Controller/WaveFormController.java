package Controller;
import View.WaveFormView;
import Model.WavFileReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.*;

public class WaveFormController {
    private WaveFormView view;

    public WaveFormController(WaveFormView view) {
        this.view = view;
    }

    public void init() {
        view.setOpenFileButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFileOpen();
            }
        });
        view.show();
    }

    private void handleFileOpen() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("WAV Sound Files", "wav"));

        int result = fileChooser.showOpenDialog(view.getMainPanel());
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            // Read WAV file samples and format
            int[][] samples = WavFileReader.readWavFile(selectedFile);
            AudioFormat format = WavFileReader.getAudioFormat(selectedFile);

            if (samples != null && format != null) {
                int sampleRate = (int) format.getSampleRate();
                int totalSamples = samples[0].length;

                // Update the view with the new waveform data
                view.updateWaveformPanel(samples[0], samples[1], sampleRate, totalSamples);
            }
        } else {
            System.out.println("File selection was canceled.");
        }
    }
}
