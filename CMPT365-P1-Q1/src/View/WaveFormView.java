package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class WaveFormView {
    private final JFrame frame;
    private final JPanel mainPanel;
    private final JButton openFileButton;
    private WaveFormPanel waveformPanel;

    public WaveFormView() {
        frame = new JFrame("WAV Waveform Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(2000, 1000);

        mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        openFileButton = new JButton("Open File");

        waveformPanel = new WaveFormPanel(null, null, 0, 0);
        waveformPanel.setPreferredSize(new Dimension(2000, 800));

        buttonPanel.add(openFileButton);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(waveformPanel, BorderLayout.CENTER);

        frame.add(mainPanel);
    }
    public void addOpenFileButtonListener(ActionListener listener) {
        openFileButton.addActionListener(listener);
    }

    public void updateWaveformPanel(int[] leftChannel, int[] rightChannel, int sampleRate, int totalSamples) {
        mainPanel.remove(waveformPanel);

        waveformPanel = new WaveFormPanel(leftChannel, rightChannel, sampleRate, totalSamples);
        mainPanel.add(waveformPanel, BorderLayout.CENTER);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void show() {
        frame.setVisible(true);
    }

    public File showOpenFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("WAV Sound Files", "wav"));
        int result = fileChooser.showOpenDialog(mainPanel);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }
}
