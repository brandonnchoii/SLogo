/**
 * 
 * @author Brandon Choi
 *
 */

package userInterface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorMessage {

    /**
     * ErrorMessage will pop up whenever there is an issue in the program such as if a user inputs
     * an invalid command
     */

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

    /**
     * initializes and creates the ErrorMessage object
     * 
     * @param String s
     */
    private void initialize (String s) {
        myStage = new Stage();
        myMessageContent = new VBox(SPACING);
        myMessageContent.setAlignment(Pos.CENTER);
        myMessageContent.getChildren().addAll(createErrorText(s), createOKButton());
    }

    /**
     * creates the appropriate error text based on the string given
     * 
     * @param String s
     * @return Text errorText
     */
    private Text createErrorText (String s) {
        errorText = new Text();
        errorText.setText(s);
        return errorText;
    }

    /**
     * creates the OK button that will close the error message
     * 
     * @return Button okButton
     */
    private Button createOKButton () {
        okButton = new Button(BUTTON_TEXT);
        okButton.setOnAction(e -> myStage.close());
        return okButton;
    }

    /**
     * displays the error message as a popup on a new stage
     */
    private void show () {
        Scene scene = new Scene(myMessageContent, MESSAGE_WIDTH, MESSAGE_HEIGHT);
        myStage.setScene(scene);
        myStage.centerOnScreen();
        myStage.show();
    }
}
