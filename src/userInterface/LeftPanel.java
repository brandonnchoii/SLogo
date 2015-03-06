package userInterface;

import java.util.Map;

import javax.security.auth.callback.Callback;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class LeftPanel {
    
    private static LeftPanel instance;
    private VBox myLeftPanel;
    private Map<String, String> myTurtles;
    private Map<String, Double> myVariables;
    private TableView turtleTable;
    private TableView<Map.Entry<String, Double>> variableTable;
    private TableColumn turtles;
    private TableColumn turtleIDs;
    private TableColumn<Map.Entry<String, Double>, String> variables;
    private TableColumn<Map.Entry<String, Double>, Double> values;
    
    public LeftPanel() {}
    
    public static synchronized LeftPanel getInstance () {
        if (instance == null) {
            instance = new LeftPanel();
            return instance;
        }
        else
            return instance;
    }

    public VBox getPanel() {
        return myLeftPanel;
    }
    
    public void initialize(ObservableMap<String,String> turtleMap, ObservableMap<String, Double> variableMap) {
        myTurtles = turtleMap;
        myVariables = variableMap;
        myVariables.put("x", Double.valueOf(125));
        
        myLeftPanel = new VBox();
        myLeftPanel.setPrefSize(UI.PANEL_WIDTH, UI.PANEL_HEIGHT);
        
        turtleTable = new TableView();
        turtles = new TableColumn("Turtle:");
        turtleIDs = new TableColumn("ID:");
        turtleTable.getColumns().addAll(turtles, turtleIDs);
        
        variableTable = new TableView();
        variables = new TableColumn("Variable:");
        values = new TableColumn("Value:");
        variableTable.getColumns().addAll(variables, values);
        
        myLeftPanel.getChildren().addAll(turtleTable, variableTable);
    }
    
    private void createEditableTable() {
        
    }
    
    private void fillTableColumn() {
        
    }
}
