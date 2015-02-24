package userInterface;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TurtleMaker {
    
    private VBox maker;
    
    public TurtleMaker() {
        initialize();
    }
    
    public VBox getTurtleMaker() {
        return maker;
    }
    
    private void initialize() {
       //utilize the same list of colors and whatnot from top bar?
        
        TextField text = new TextField("NEW TURTLE NAME");
        
        
        maker.getChildren().addAll(text);
    }
}
