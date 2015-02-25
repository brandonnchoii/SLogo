/**
 *
 * @author Brandon Choi
 *
 */

package userInterface;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TurtleMaker {
    
    private VBox turtleMaker;
    
    public TurtleMaker() {
        initialize();
    }
    
    public void makeTurtle() {
        Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, 250, 200);

        root.getChildren().add(turtleMaker);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
    
    private void initialize() {
        turtleMaker = new VBox();
        TextField text = new TextField("NEW TURTLE NAME");
        text.setOnMouseClicked(e -> text.clear());
        HBox colorPick = new HBox(10);
        colorPick.getChildren().addAll(new Label("Pen Color: "), new ColorPicker());
        turtleMaker.getChildren().addAll(text, colorPick);
    }
}
