package userInterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UI {

    public static final int UI_WIDTH = 1000;
    public static final int UI_HEIGHT = 750;
    public static final int PANEL_WIDTH = 200;
    public static final int PANEL_HEIGHT = 500;
    
    private static final String AUTOMATED_TEXT = "Click here to type commands!";
    
    private static final int INPUT_WIDTH = 450;
    private static final int INPUT_HEIGHT = 200;
    
    private TabPane myUI;
    private BorderPane myView;
    private BorderPane myCenterView;
    private TextArea myInput;
    private Scene myScene;
    private RightPanel myRightPanel;
    private LeftPanel myLeftPanel;
    
    public UI () {
        initialize();
    }
    
    public Scene getScene() {
        return myScene;
    }
    
    private void initialize() {
        myUI = new TabPane();
        myView = new BorderPane();
        myView.setPadding(new Insets(10,10,10,10));
        
        myCenterView = new BorderPane();
        myCenterView.setStyle("-fx-background: red;");
        
        myScene = new Scene(myUI, UI_WIDTH, UI_HEIGHT);
        myRightPanel = new RightPanel();
        //myLeftPanel = new LeftPanel();
        
        
        addNewTab("SLogo #1", myView);  
        setUpCommandField();
        
        myView.setCenter(myCenterView);
        
        myCenterView.setBottom(myInput);
        myCenterView.setAlignment(myInput, Pos.CENTER);
        
        myView.setRight(myRightPanel.getPanel());
        myView.setLeft(myLeftPanel.getPanel());
    }

    private void setUpCommandField () {
        myInput = new TextArea(AUTOMATED_TEXT);
        myInput.setMaxSize(INPUT_WIDTH, INPUT_HEIGHT);
        
        myInput.setOnMouseClicked(e -> {
            if (myInput.getText().equals(AUTOMATED_TEXT))
                myInput.clear();
        });
    }
    
    private void addNewTab(String text, Node content) {
        Tab tab = new Tab();
        tab.setText(text);
        tab.setContent(content);
        myUI.getTabs().add(tab);
    }
}
