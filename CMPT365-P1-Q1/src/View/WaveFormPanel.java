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
        g.drawString("Total number of the samples: " + totalSamples, 10, 20);
        g.drawString("Sampling frequency: " + sampleRate + " Hz", 10, 40);

        int boxX = 50;
        int boxY = 50;
        int boxWidth = getWidth() - 100;
        int boxHeight = getHeight() - 100;

        g.setColor(Color.BLACK);
        g.fillRect(boxX, boxY, boxWidth, boxHeight);

        int halfHeight = boxHeight / 2;

        g.setColor(Color.GREEN);
        drawWaveform(g, leftChannelSamples, boxX, boxY, halfHeight);

        g.setColor(Color.GREEN);
        drawWaveform(g, rightChannelSamples, boxX, boxY + halfHeight, halfHeight);
    }

private void drawWaveform(Graphics g, int[] samples, int xOffset, int yOffset, int panelHeight) {
    if (samples == null){
        return;
    }
    int panelWidth = getWidth() - 100;  // adjust to match the smaller box width
    int midY = (panelHeight) / 2 + yOffset; // middle of the box area for Y axis
    // determine step size to fit the waveform in the box
    int step = Math.max(1, samples.length / panelWidth);

    // normalize the amplitude
    double scale = (panelHeight / 2.0) / 32768.0; //15 bits

    for (int i = 0; i < panelWidth - 1; i++) {
        int sample1 = samples[i * step];
        int sample2 = samples[(i + 1) * step];

        int y1 = (int) (midY - sample1 * scale);
        int y2 = (int) (midY - sample2 * scale);

        g.drawLine(xOffset + i, y1, xOffset + i + 1, y2);
    }
}
}
