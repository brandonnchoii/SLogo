package userInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

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

    
    public static final int PANEL_WIDTH = 200;
    public static final int PANEL_HEIGHT = 500;
    
    private static final int INPUT_WIDTH = 450;
    private static final int INPUT_HEIGHT = 300;
    
    private WorldController myController;
    private BorderPane myView, myCenterView;
    private TextArea myInput;
    private boolean textClicked;
    private Scene myScene;
    private RightPanel myRightPanel;
    private LeftPanel myLeftPanel;
    private TopMenu myMenu;
    private Canvas myCanvas;
    private GraphicsContext myGC;
    private StackPane canvasPane;
    private EventHandler<ActionEvent> run;
    
    public UI (){
        initialize();
        //setUpController();
    }
    
    public Pane getUI () {
        return myView;
    }
    
    private void initialize() {
        myView = new BorderPane();
        myView = new BorderPane();
        myView.setPadding(new Insets(10,10,10,10));
        myCenterView = new BorderPane();
        myCenterView.setPadding(new Insets(5,5,5,5));  
        
        setUpEventHandlers();
        setUpCommandField();
        
        myRightPanel = new RightPanel(run);
        myLeftPanel = new LeftPanel(new HashMap<String, String>(), new HashMap<String, Double>());

        myView.setCenter(myCenterView);
        myView.setRight(myRightPanel.getPanel());
        myView.setLeft(myLeftPanel.getPanel());
        myCenterView.setBottom(myInput);
        myCenterView.setAlignment(myInput, Pos.CENTER);
    }
    
    private void setUpController(){
        try {
            myController = new WorldController(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void setUpEventHandlers () {
        run = e -> runCommand();
    }

    private void runCommand () {
        System.out.println("works");
        //myController.update(myInput.getText());
    }

    private void setUpCommandField () {
        myInput = new TextArea("HELLO");
       // myInput = new TextArea(language.getString("InputText"));
        textClicked = false;
        myInput.setMaxSize(INPUT_WIDTH, INPUT_HEIGHT); 
        myInput.setOnMouseClicked(e -> {
            if (!textClicked) 
                myInput.clear();
                textClicked = true;
        });
    }
}
