package Model;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class WaveFormModel {
    private int[][] samples;
    private int sampleRate;
    private int totalSamples;
    public void readWavFile(File wavFile) throws UnsupportedAudioFileException, IOException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(wavFile);
        AudioFormat audioFormat = audioInputStream.getFormat();

        if (audioFormat.getChannels() != 2 || audioFormat.getSampleSizeInBits() != 16) {
            throw new IllegalArgumentException("Unsupported audio format. Only 16-bit stereo WAV files are supported.");
        }

        int frameSize = audioFormat.getFrameSize();
        long numFrames = audioInputStream.getFrameLength();
        int numSamples = (int) numFrames;

        int[] leftChannelSamples = new int[numSamples];
        int[] rightChannelSamples = new int[numSamples];
        byte[] audioBuffer = new byte[frameSize];
        int sampleIndex = 0;

        while ((audioInputStream.read(audioBuffer)) != -1 && sampleIndex < numSamples) {
            byte leftByte1 = audioBuffer[0];
            byte leftByte2 = audioBuffer[1];
            leftChannelSamples[sampleIndex] = ((leftByte2 << 8) | (leftByte1 & 0xFF));

            byte rightByte1 = audioBuffer[2];
            byte rightByte2 = audioBuffer[3];
            rightChannelSamples[sampleIndex] = ((rightByte2 << 8) | (rightByte1 & 0xFF));

            sampleIndex++;
        }
        audioInputStream.close();

        samples = new int[][]{leftChannelSamples, rightChannelSamples};
        sampleRate = (int) audioFormat.getSampleRate();
        totalSamples = samples[0].length + samples[1].length;
    }
    public int getSampleRate(){
        return sampleRate;
    }

    public int getTotalSamples() {
        return totalSamples;
    }

    public int[][] getSamples() {
        return samples;
    }
}
