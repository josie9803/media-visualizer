import javax.swing.*;
import java.awt.*;

public class WaveFormPanel extends JPanel {
    private int[] leftChannelSamples;
    private int[] rightChannelSamples;
    private int sampleRate;
    private int totalSamples;

    public WaveFormPanel(int[] leftChannelSamples, int[] rightChannelSamples, int sampleRate, int totalSamples) {
        this.leftChannelSamples = leftChannelSamples;
        this.rightChannelSamples = rightChannelSamples;
        this.sampleRate = sampleRate;
        this.totalSamples = totalSamples;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Divide the height into two halves, one for each channel
        int halfHeight = panelHeight / 2;

        // Display the total samples and sample rate at the top of the panel
        g.setColor(Color.BLACK);
        g.drawString("Total Samples: " + totalSamples, 10, 20);
        g.drawString("Sample Rate: " + sampleRate + " Hz", 10, 40);

        // Draw left channel (top)
        if (leftChannelSamples != null) {
            g.setColor(Color.BLUE);
            drawWaveform(g, leftChannelSamples, 0, halfHeight);
        }

        // Draw right channel (bottom)
        if (rightChannelSamples != null) {
            g.setColor(Color.RED);
            drawWaveform(g, rightChannelSamples, halfHeight, panelHeight);
        }
    }

    private void drawWaveform(Graphics g, int[] samples, int yOffset, int panelHeight) {
        int panelWidth = getWidth();
        int midY = (panelHeight - yOffset) / 2 + yOffset; // Middle of the panel (Y axis)

        // Determine step size to fit the waveform in the panel
        int step = Math.max(1, samples.length / panelWidth);

        // Normalize the amplitude (assuming 16-bit signed PCM, range -32768 to 32767)
        double scale = (panelHeight / 2.0) / 32768.0;

        // Draw the waveform using lines
        for (int i = 0; i < panelWidth - 1; i++) {
            int sample1 = samples[i * step];
            int sample2 = samples[(i + 1) * step];

            int y1 = (int) (midY - sample1 * scale);
            int y2 = (int) (midY - sample2 * scale);

            g.drawLine(i, y1, i + 1, y2);
        }
    }
}
