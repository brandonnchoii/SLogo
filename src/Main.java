import javafx.application.Application;
import javafx.stage.Stage;
import userInterface.UI;
import userInterface.UserInterface;

public class Main extends Application {

    @Override
    public void start (Stage stage) throws Exception {
        //UserInterface display = new UserInterface();
        UI display = new UI();
        stage.setScene(display.getScene());
        stage.show();
        //display.setUpController();
    }

    public static void main (String[] args) {
        launch(args);
    }

}
