package userInterface;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CommandSaver {
    
    private static final int BUTTON_WIDTH = 150;
    
    private VBox saveCommandWindow;
    
    public CommandSaver() {
        initialize();
        saveCommand();
    }
    
    public void saveCommand() {
        Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root);

        root.getChildren().add(saveCommandWindow);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
    
    private void initialize() {
        saveCommandWindow = new VBox();
        Button saveButton = new Button ("Save");
        saveButton.setMinWidth(BUTTON_WIDTH);    
        //saveButton.setOnMouseClicked(e -> ...);
        TextArea commandWindow = new TextArea("Enter SLogo command(s) you want to save HERE.");
        commandWindow.setOnMouseClicked(e -> commandWindow.clear());
        saveCommandWindow.getChildren().addAll(commandWindow, saveButton);
    }
}
