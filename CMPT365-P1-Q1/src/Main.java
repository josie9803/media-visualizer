import Model.WaveFormModel;
import View.WaveFormView;
import Controller.WaveFormController;

public class Main {
    public static void main(String[] args) {
        WaveFormModel model = new WaveFormModel();
        WaveFormView view = new WaveFormView();
        WaveFormController controller = new WaveFormController(model, view);
        controller.init();
    }
}
