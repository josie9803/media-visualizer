package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WaveFormView {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton openFileButton;
    private WaveFormPanel waveformPanel;

    public WaveFormView() {
        // Setup the main frame
        frame = new JFrame("WAV Waveform Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 1000);

        // Setup panels and button
        mainPanel = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new FlowLayout());
        openFileButton = new JButton("Open File");

        // Initialize empty waveform panel
        waveformPanel = new WaveFormPanel(null, null, 0, 0);
        waveformPanel.setPreferredSize(new Dimension(2000, 800));

        // Add the button to the button panel
        buttonPanel.add(openFileButton);

        // Add panels to the main frame
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(waveformPanel, BorderLayout.CENTER);

        // Add the main panel to the frame
        frame.add(mainPanel);
    }
    public void setOpenFileButtonListener(ActionListener listener) {
        openFileButton.addActionListener(listener);
    }

    public void updateWaveformPanel(int[] leftChannel, int[] rightChannel, int sampleRate, int totalSamples) {
        // Remove the old waveform panel
        mainPanel.remove(waveformPanel);

        // Update with a new waveform panel
        waveformPanel = new WaveFormPanel(leftChannel, rightChannel, sampleRate, totalSamples);
        mainPanel.add(waveformPanel, BorderLayout.CENTER);

        // Refresh the frame
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void show() {
        frame.setVisible(true);
    }

    public Component getMainPanel() {
        return mainPanel;
    }
}
