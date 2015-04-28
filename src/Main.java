import javafx.application.Application;
import javafx.stage.Stage;
import userInterface.UIManager;

public class Main extends Application {

    @Override
    public void start (Stage stage) throws Exception {
        UIManager display = new UIManager();
        stage.setScene(display.getScene());
        stage.show();
    }

    public static void main (String[] args) {
        launch(args);
    }
}
