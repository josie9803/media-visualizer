package Model;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class WaveFormModel {
    private int[][] samples; // Stores left and right channel samples
    private AudioFormat audioFormat; // Stores audio format information

    public WaveFormModel(File wavFile) throws UnsupportedAudioFileException, IOException {
        readWavFile(wavFile);
    }

    private void readWavFile(File wavFile) throws UnsupportedAudioFileException, IOException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(wavFile);
        audioFormat = audioInputStream.getFormat();

        // Check for supported audio format (16-bit stereo)
        if (audioFormat.getChannels() != 2 || audioFormat.getSampleSizeInBits() != 16) {
            throw new IllegalArgumentException("Unsupported audio format. Only 16-bit stereo WAV files are supported.");
        }

        int frameSize = audioFormat.getFrameSize();
        long numFrames = audioInputStream.getFrameLength();
        int numSamples = (int) numFrames;

        // Arrays for left and right channel samples
        int[] leftChannelSamples = new int[numSamples];
        int[] rightChannelSamples = new int[numSamples];

        byte[] audioBuffer = new byte[frameSize];
        int bytesRead;
        int sampleIndex = 0;

        while ((bytesRead = audioInputStream.read(audioBuffer)) != -1 && sampleIndex < numSamples) {
            // Left channel
            byte leftByte1 = audioBuffer[0];
            byte leftByte2 = audioBuffer[1];
            leftChannelSamples[sampleIndex] = ((leftByte2 << 8) | (leftByte1 & 0xFF));

            // Right channel
            byte rightByte1 = audioBuffer[2];
            byte rightByte2 = audioBuffer[3];
            rightChannelSamples[sampleIndex] = ((rightByte2 << 8) | (rightByte1 & 0xFF));

            sampleIndex++;
        }

        audioInputStream.close();
        samples = new int[][]{leftChannelSamples, rightChannelSamples};
    }

    public int[][] getSamples() {
        return samples;
    }

    public AudioFormat getAudioFormat() {
        return audioFormat;
    }
}
