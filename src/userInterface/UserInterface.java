package userInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import turtle.Turtle;
import worldController.WorldController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class UserInterface {

    private static final int myWidth = 850;
    private static final int myHeight = 700;
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
    private static final String DEFAULT_IMAGE_PACKAGE = "resources/images/";
    private static final String[] buttons = { "ChangeTurtleCommand", "RunCommand", "SaveCommand" };
    
    private WorldController myController;
    private VBox mySidebar;
    private Scene myScene;
    private BorderPane myRoot;
    private ResourceBundle myMenuNames;
    private TextArea myCommandWindow;
    private Timeline myAnimation;
    private HBox myTopbar;
    
    private Canvas myCanvas;
    private GraphicsContext myGC;
    
    public UserInterface () {
        myRoot = new BorderPane();
        myRoot.setPadding(new Insets(15, 20, 15, 20));
        myCanvas = new Canvas(300, 300);
        myGC = myCanvas.getGraphicsContext2D();
        myMenuNames = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
        mySidebar = makeSidebar();
        myTopbar = makeTopbar();
        myCommandWindow = makeCommandWindow();        
        makeTimeline();
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
        myRoot.setRight(mySidebar);
    }
    
    private TextArea makeCommandWindow() {
        TextArea commandWindow = new TextArea("Enter SLogo commands HERE.");
        commandWindow.setOnMouseClicked(e -> commandWindow.clear());
        return commandWindow;
    }

    private VBox makeSidebar () {
        Sidebar sidebar = new Sidebar();
        makeFunctionButtons(sidebar);
        return sidebar.getSidebar();
    }

    private HBox makeTopbar () {
        Topbar topbar = new Topbar();
        return topbar.getTopbar();
    }
    
    private void makeFunctionButtons(Sidebar sidebar) {
        makeAddButton(sidebar);
    	makeSwitchTurtle(sidebar);
        makeSaveButton(sidebar);
    	makeRunButton(sidebar);
    }


    private void makeSwitchTurtle (Sidebar sidebar) {
        Button save = makeUIButton("ChangeTurtleCommand", e -> changeTurtle());
    	sidebar.getSidebar().getChildren().add(save);
    }


    private void makeSaveButton(Sidebar sidebar) {
        Button save = makeUIButton("SaveCommand", e -> saveCommand(myCommandWindow.getText()));
        sidebar.getSidebar().getChildren().add(save);
    }

    private void makeAddButton(Sidebar sidebar) {
    	Button save = makeUIButton("AddTurtle", e -> addTurtle());
        sidebar.getSidebar().getChildren().add(save);
    }
     
    private void addTurtle() {
		// TODO Auto-generated method stub
	}

	// open file chooser for turtle image
    // need to make a default (probably xml)
    private void changeTurtle () {

    }

    private void runCommands (String s) {
        String data = s;
        myController.update();
    }

    private void saveCommand (String s) {
    }
    
    private void makeTimeline() {
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Animation.INDEFINITE);
		myAnimation.play();
	}

    private Button makeUIButton (String s, EventHandler<MouseEvent> e) {
        Button test = new Button(myMenuNames.getString(s));
        test.setMaxWidth(Double.MAX_VALUE);
        test.setOnMousePressed(e);
        return test;
    }
    
    private void makeRunButton(Sidebar sidebar) {
        Button play = new Button();
    	play.setOnMousePressed(e -> runCommands(myCommandWindow.getText()));
        Image playImage = new Image(DEFAULT_IMAGE_PACKAGE + "PlayButton.jpg");
        ImageView playView = new ImageView(playImage);
        playView.setScaleX(.9);
        playView.setScaleY(.9);
        play.setGraphic(playView);
        sidebar.getSidebar().getChildren().add(play);
    }

    public void refresh (String s) {

    }
    
}
