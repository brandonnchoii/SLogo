package userInterface;

import java.util.Map;

import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class LeftPanel {
    
    private VBox myLeftPanel;
    private Map<String, String> myTurtles;
    private Map<String, Double> myVariables;
    private TableView turtleTable;
    private TableView variableTable;
    
    public LeftPanel(Map<String,String> turtles, Map<String,Double> variables) {
        myTurtles = turtles;
        myVariables = variables;
        
        initialize();
    }

    public VBox getPanel() {
        return myLeftPanel;
    }
    
    private void initialize() {
        myLeftPanel = new VBox();
        
    }
}
