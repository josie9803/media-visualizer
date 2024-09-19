package Model;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class WavFileReader {

    public static int[][] readWavFile(File wavFile) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(wavFile);
            AudioFormat format = audioInputStream.getFormat();

            if (format.getChannels() != 2 || format.getSampleSizeInBits() != 16) {
                System.out.println("Unsupported audio format. This example only supports 16-bit stereo WAV files.");
                return null;
            }

            int frameSize = format.getFrameSize();
            long numFrames = audioInputStream.getFrameLength();
            int numSamples = (int) numFrames;

            // Arrays for left and right channel samples
            int[] leftChannelSamples = new int[numSamples];
            int[] rightChannelSamples = new int[numSamples];

            byte[] audioBuffer = new byte[frameSize];
            int bytesRead;
            int sampleIndex = 0;

            while ((bytesRead = audioInputStream.read(audioBuffer)) != -1) {
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
            return new int[][]{leftChannelSamples, rightChannelSamples};

        } catch (UnsupportedAudioFileException | IOException e) {
            System.err.println("Error reading WAV file: " + e.getMessage());
        }

        return null;
    }

    public static AudioFormat getAudioFormat(File wavFile) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(wavFile);
            return audioInputStream.getFormat();
        } catch (UnsupportedAudioFileException | IOException e) {
            System.err.println("Error getting audio format: " + e.getMessage());
        }
        return null;
    }
}
