import javafx.application.Application;
import javafx.stage.Stage;
import userInterface.UserInterface;


public class Main extends Application{
    
	@Override
	public void start(Stage arg0) throws Exception {
		UserInterface display = new UserInterface();
	}

	 public static void main (String [] args) {	        
			launch(args);
	 }

}
