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
    protected Button saveButton;
    protected TextArea commandWindow;
    private Stage stage;

    public CommandSaver () {
        initialize();
    }

    protected void saveCommand () {
        stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(saveCommandWindow);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    protected void close () {
        stage.close();
    }

    private void initialize () {
        saveCommandWindow = new VBox();
        saveButton = new Button("Save");
        saveButton.setMinWidth(BUTTON_WIDTH);
        // saveButton.setOnMouseClicked(e -> ...);
        commandWindow = new TextArea("Enter SLogo command(s) you want to save HERE.");
        commandWindow.setOnMouseClicked(e -> commandWindow.clear());
        saveCommandWindow.getChildren().addAll(commandWindow, saveButton);
    }
}
