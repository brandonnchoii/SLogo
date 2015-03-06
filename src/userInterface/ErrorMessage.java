package userInterface;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ErrorMessage {
    
    public ErrorMessage () {
        initialize();
        show();
    }
    
    private void initialize() {
        
    }
    
    private void show() {
        Stage myStage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, 300, 400);
        //root.getChildren().add();
        myStage.setScene(scene);
        myStage.show();
        myStage.centerOnScreen();
    }
}
