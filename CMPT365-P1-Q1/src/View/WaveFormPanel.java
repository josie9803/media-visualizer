package View;
import javax.swing.*;
import java.awt.*;

public class WaveFormPanel extends JPanel {
    private final int[] leftChannelSamples;
    private final int[] rightChannelSamples;
    private final int sampleRate;
    private final int totalSamples;

    public WaveFormPanel(int[] leftChannelSamples, int[] rightChannelSamples, int sampleRate, int totalSamples) {
        this.leftChannelSamples = leftChannelSamples;
        this.rightChannelSamples = rightChannelSamples;
        this.sampleRate = sampleRate;
        this.totalSamples = totalSamples;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawString("Total Samples: " + totalSamples, 10, 20);
        g.drawString("Sample Rate: " + sampleRate + " Hz", 10, 40);

        // Set dimensions for the waveform box
        int boxX = 50;    // Horizontal offset from the left edge
        int boxY = 50;    // Vertical offset from the top edge
        int boxWidth = getWidth() - 100;   // Width of the box (leaving space on both sides)
        int boxHeight = getHeight() - 100; // Height of the box (leaving space on top and bottom)

        // Set the background to black for the waveform area
        g.setColor(Color.BLACK);
        g.fillRect(boxX, boxY, boxWidth, boxHeight);  // Fill the specified area with black

        // Divide the box height into two halves, one for each channel
        int halfHeight = boxHeight / 2;

        // Draw the left channel waveform (top) inside the black box
        g.setColor(Color.GREEN);
        drawWaveform(g, leftChannelSamples, boxX, boxY, halfHeight);

        // Draw the right channel waveform (bottom) inside the black box
        g.setColor(Color.GREEN);
        drawWaveform(g, rightChannelSamples, boxX, boxY + halfHeight, halfHeight);

    }

private void drawWaveform(Graphics g, int[] samples, int xOffset, int yOffset, int panelHeight) {
    if (samples == null){
        return;
    }
    int panelWidth = getWidth() - 100;  // Adjust to match the smaller box width
    int midY = (panelHeight) / 2 + yOffset; // Middle of the box area for Y axis
    // Determine step size to fit the waveform in the box
    int step = Math.max(1, samples.length / panelWidth);

    // Normalize the amplitude
    double scale = (panelHeight / 2.0) / 32768.0; //15 bits

    // Draw the waveform within the specified box area
    for (int i = 0; i < panelWidth - 1; i++) {
        int sample1 = samples[i * step];
        int sample2 = samples[(i + 1) * step];

        int y1 = (int) (midY - sample1 * scale);
        int y2 = (int) (midY - sample2 * scale);

        g.drawLine(xOffset + i, y1, xOffset + i + 1, y2);  // Draw line inside the black box
    }
}
}
