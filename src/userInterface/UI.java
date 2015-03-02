package userInterface;

import java.io.IOException;
import java.util.HashMap;

import worldController.WorldController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UI {

    public static final int UI_WIDTH = 1000;
    public static final int UI_HEIGHT = 750;
    public static final int PANEL_WIDTH = 200;
    public static final int PANEL_HEIGHT = 500;
    
    private static final String TAB_NAME = "SLogo #";
    private static final String AUTOMATED_TEXT = "Click here to type commands!";
    private static final int INPUT_WIDTH = 450;
    private static final int INPUT_HEIGHT = 300;
    
    private WorldController myController;
    private TabPane myUI; //singleton for TabPane
    private BorderPane myView, myCenterView;
    private TextArea myInput;
    private Scene myScene;
    private RightPanel myRightPanel;
    private LeftPanel myLeftPanel;
    private TopMenu myMenu;
    private Canvas myCanvas;
    private GraphicsContext myGC;
    private StackPane canvasPane;
    
    private EventHandler<ActionEvent> run, changeColor;
    
    public UI () {
        initialize();
    }
    
    public Scene getScene() {
        return myScene;
    }
    
    public void setUpController() throws IOException {
        myController = new WorldController(this);
    }
    
    public Pane getView() {
        return myView;
    }
    
    private void initialize() {
        myUI = new TabPane();
        myView = new BorderPane();
        myView.setPadding(new Insets(10,10,10,10));
        
        myCenterView = new BorderPane();
        myCenterView.setPadding(new Insets(5,5,5,5));
        
        myScene = new Scene(myUI, UI_WIDTH, UI_HEIGHT);
        
        setUpEventHandlers();
        
        myRightPanel = new RightPanel(run);
        myLeftPanel = new LeftPanel(new HashMap<String, String>(), new HashMap<String, Double>());
        myMenu = new TopMenu();
        
        addNewTab(myView);  
        setUpCommandField();
        
        myView.setCenter(myCenterView); 
        myCenterView.setTop(myMenu.getMenuBar());
        myCenterView.setBottom(myInput);
        myCenterView.setAlignment(myInput, Pos.CENTER);
        
        myView.setRight(myRightPanel.getPanel());
        myView.setLeft(myLeftPanel.getPanel());
    }
    
    private void setUpEventHandlers () {
        run = e -> runCommand();
    }

    private void runCommand () {
        System.out.println("works");
        //myController.update(myInput.getText());
    }

    private void setUpCommandField () {
        myInput = new TextArea(AUTOMATED_TEXT);
        myInput.setMaxSize(INPUT_WIDTH, INPUT_HEIGHT); 
        myInput.setOnMouseClicked(e -> {
            if (myInput.getText().equals(AUTOMATED_TEXT))
                myInput.clear();
        });
    }
    
    private void addTab(Node content) {
        Tab tab = new Tab();
        tab.setText(TAB_NAME + (myUI.getTabs().size() + 1));
        
        
    }
    
    private void addNewTab(Node content) {
        Tab tab = new Tab();
        tab.setText(TAB_NAME + (myUI.getTabs().size() + 1));
        tab.setContent(content);
        myUI.getTabs().add(tab);
    }
}
