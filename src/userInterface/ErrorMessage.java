package userInterface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorMessage {
    
    private static final int MESSAGE_WIDTH = 250;
    private static final int MESSAGE_HEIGHT = 150;
    private static final int SPACING = 10;
    private static final String BUTTON_TEXT = "OK";
    private Stage myStage;
    private VBox myMessageContent;
    private Text errorText;
    private Button okButton;
    
    public ErrorMessage (String error) {
        initialize(error);
        show();
    }
    
    private void initialize(String s) {
        myStage = new Stage();
        myMessageContent = new VBox(SPACING);
        myMessageContent.setAlignment(Pos.CENTER);
        myMessageContent.getChildren().addAll(createErrorText(s), createOKButton());
    }

    private Text createErrorText (String s) {
        errorText = new Text();
        errorText.setText(s);
        return errorText;
    }
    
    private Button createOKButton () {
        okButton = new Button(BUTTON_TEXT);
        okButton.setOnAction(e -> myStage.close());
        return okButton;
    }
    
    private void show() {
        Scene scene = new Scene(myMessageContent, MESSAGE_WIDTH, MESSAGE_HEIGHT);
        myStage.setScene(scene);
        myStage.centerOnScreen();
        myStage.show();
    }
}
