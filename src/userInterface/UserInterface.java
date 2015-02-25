package userInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import turtle.Turtle;
import worldController.WorldController;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
* 
* @author Brandon Choi, James Mosca
*
*/
public class UserInterface {

    public static final int BOX_WIDTH = 180;
    private static final int myWidth = 900;
    private static final int myHeight = 750;
    private static final double SCALER = .7;
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
    private static final String DEFAULT_IMAGE_PACKAGE = "resources/images/";
    
    private WorldController myController;
    private Scene myScene;
    private BorderPane myRoot;
    private Sidebar mySidebar;
    private Topbar myTopbar;
    private ResourceBundle myMenuNames;
    private TextArea myCommandWindow;
    private Timeline myAnimation;

    private Canvas myCanvas;
    private GraphicsContext myGC;
    
    public UserInterface () {
        myRoot = new BorderPane();
        myRoot.setPadding(new Insets(15, 20, 15, 20));
        mySidebar = new Sidebar();
        makeCreateTurtle();
        makeSaveCommand();
        makeRunButton();
        myTopbar = new Topbar();

        // addTurtle();
        // myMenuNames = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "UIMenu");

        myCanvas = new Canvas(200, 200);
        myGC = myCanvas.getGraphicsContext2D();
        myMenuNames = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");

        myCommandWindow = makeCommandWindow();
        changeColor(Color.RED);
        myGC.fillRect(100, 100, 50, 50);
        
        makeTimeline();

        myRoot.setBottom(myCommandWindow);
        myRoot.setRight(mySidebar.getSidebar());
        myRoot.setTop(myTopbar.getTopbar());
        setupPane();
        myScene = new Scene(myRoot, myWidth, myHeight);
    }

    public Scene getScene () {
        return myScene;
    }
    
    private void setupPane() {
    	StackPane p = new StackPane();
        myRoot.setCenter(p);
    	p.setStyle("-fx-background-color: white");
    	p.getChildren().add(myCanvas);
        myRoot.setBottom(myCommandWindow);
        myRoot.setRight(mySidebar.getSidebar());
    }
    
    private TextArea makeCommandWindow() {
        TextArea commandWindow = new TextArea("Enter SLogo command(s) HERE.");
        commandWindow.setOnMouseClicked(e -> commandWindow.clear());
        return commandWindow;
    }

    private void runCommands (String s) {
        String data = s;
        myController.update();
    }
    
    private void makeCreateTurtle() {
        Button turtleCreate = new Button("Create New Turtle");
        turtleCreate.setMinWidth(BOX_WIDTH);
        turtleCreate.setOnMouseClicked(e -> new TurtleMaker());
        mySidebar.add(turtleCreate);
    }

    private void makeSaveCommand() {
        Button saveCommand = new Button("Save New Command");
        saveCommand.setMinWidth(BOX_WIDTH);
        saveCommand.setOnMouseClicked(e -> new CommandSaver());
        mySidebar.add(saveCommand);
    }
    
    private void changeColor(Color color) {
    	myGC.setFill(color);
    }
    
    private void makeTimeline() {
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Animation.INDEFINITE);
		myAnimation.play();
	}

    private Button makeUIButton (EventHandler<MouseEvent> e) {
        Button test = new Button();
        test.setMaxWidth(Double.MAX_VALUE);
        test.setOnMousePressed(e);
        return test;
    }
    
    private void makeRunButton() {
        Button play = makeUIButton(e -> runCommands(myCommandWindow.getText()));
        Image playImage = new Image(DEFAULT_IMAGE_PACKAGE + "PlayButton.jpg");
        ImageView playView = new ImageView(playImage);
        playView.setFitWidth(60);
        playView.setFitHeight(40);
        play.setGraphic(playView);
        mySidebar.add(play);
    }

    public void refresh (String s) {

    }
}
