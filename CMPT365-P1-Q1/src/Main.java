import View.WaveFormView;
import Controller.WaveFormController;

public class Main {
    public static void main(String[] args) {
        WaveFormView view = new WaveFormView();
        WaveFormController controller = new WaveFormController(view);
        controller.init();
    }
}
