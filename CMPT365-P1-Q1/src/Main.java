import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.*;

public class Main {
    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame("WAV Waveform Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 1000);

        // Create a main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a panel for the "Open File" button
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Create a button labeled "Open File"
        JButton openFileButton = new JButton("Open File");

        // Create an empty panel for displaying the waveform
        final WaveFormPanel[] waveformPanel = {new WaveFormPanel(null, null, 0, 0)};
        waveformPanel[0].setPreferredSize(new Dimension(2000, 800)); // Reserve space for the waveform

        // Add an action listener to the button
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a file chooser
                JFileChooser fileChooser = new JFileChooser();

                // Set the file filter to only allow WAV files
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("WAV Sound Files", "wav"));

                // Open the file chooser dialog and capture the user's response
                int result = fileChooser.showOpenDialog(frame);

                // If the user selects a file, proceed
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Get the selected file
                    File selectedFile = fileChooser.getSelectedFile();

                    // Display the file path of the selected WAV file
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());

                    // Use WavFileReader to read the left and right channel samples
                    int[][] samples = WavFileReader.readWavFile(selectedFile);
                    AudioFormat format = WavFileReader.getAudioFormat(selectedFile);  // Get audio format

                    if (samples == null || format == null) {
                        System.out.println("Failed to load WAV file.");
                        return;
                    }

                    int[] leftChannelSamples = samples[0];
                    int[] rightChannelSamples = samples[1];

                    // Get the sample rate and total number of samples
                    int sampleRate = (int) format.getSampleRate();
                    int totalSamples = leftChannelSamples.length;

                    // Create a new WaveFormPanel with the samples, sample rate, and total number of samples
                    WaveFormPanel newWaveFormPanel = new WaveFormPanel(leftChannelSamples, rightChannelSamples, sampleRate, totalSamples);

                    // Remove the old waveform panel and add the new one
                    mainPanel.remove(waveformPanel[0]);  // Remove the old panel
                    waveformPanel[0] = newWaveFormPanel;  // Update the reference to the new panel
                    mainPanel.add(waveformPanel[0], BorderLayout.CENTER);  // Add the new panel in the center

                    // Refresh the frame to show the updated panel
                    mainPanel.revalidate();  // Revalidate the layout
                    mainPanel.repaint();     // Repaint to show changes
                } else {
                    // If the user canceled the dialog
                    System.out.println("File selection was canceled.");
                }
            }
        });

        // Add the button to the button panel
        buttonPanel.add(openFileButton);

        // Add the button panel to the main panel at the top
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        // Add the empty waveform panel to the center of the main panel
        mainPanel.add(waveformPanel[0], BorderLayout.CENTER);

        // Add the main panel to the frame
        frame.add(mainPanel);

        // Make the window visible
        frame.setVisible(true);
    }
}
