package Controller;
import Model.WaveFormModel;
import View.WaveFormView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class WaveFormController {
    private final WaveFormView view;
    private final WaveFormModel model;

    public WaveFormController(WaveFormModel model, WaveFormView view) {
        this.model = model;
        this.view = view;
        view.show();
    }

    public void init() {
        view.addOpenFileButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFileOpen();
            }
        });
    }
    private void handleFileOpen() {
        File selectedFile = view.showOpenFileDialog();
        if (selectedFile != null) {
            try {
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                model.readWavFile(selectedFile);
                int[][] samples = model.getSamples();

                if (samples != null) {
                    int sampleRate = model.getSampleRate();
                    int totalSamples = model.getTotalSamples();
                    view.updateWaveformPanel(samples[0], samples[1], sampleRate, totalSamples);
                }
            } catch (Exception e) {
                System.err.println("Error reading WAV file: " + e.getMessage());
            }
        }
    }
}
