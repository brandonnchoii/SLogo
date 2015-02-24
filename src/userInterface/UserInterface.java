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
import javafx.scene.effect.ColorInput;
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
    
    private WorldController myController;
    private List<Turtle> turtleList;

    // private HBox commandField;

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
        turtleList = new ArrayList<Turtle>();

        // addTurtle();
        // myMenuNames = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "UIMenu");

        myCanvas = new Canvas(300, 300);
        myGC = myCanvas.getGraphicsContext2D();
        myMenuNames = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");

        mySidebar = makeSidebar();
        myTopbar = makeTopbar();
        myCommandWindow = makeCommandWindow();
        changeColor(Color.RED);
        myGC.fillRect(100, 100, 50, 50);
        
        makeTimeline();

        // myRoot.getChildren().addAll(myCommandWindow);
        myRoot.setBottom(myCommandWindow);
        myRoot.setRight(mySidebar);
        myRoot.setTop(myTopbar);
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
        makeRunButton(sidebar);
        return sidebar.getSidebar();
    }

    private HBox makeTopbar () {
        Topbar topbar = new Topbar();
        return topbar.getTopbar();
    }
    
    private void makeFunctionButtons() {
        
    }

    private static final String[] buttons = { "ChangeTurtleCommand", "RunCommand", "SaveCommand" };

//    private Button makeTurtleButton () {
//        return makeUIButton("ChangeTurtleCommand", e -> changeTurtle());
//    }
//
//    private Button makeRunButton () {
//        return makeUIButton("RunCommand", e -> runCommands(myCommandWindow.getText()));
//    }
//
//    private Button saveCommandButton () {
//        return makeUIButton("SaveCommand", e -> saveCommand(myCommandWindow.getText()));
//    }

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
    
    private void makeRunButton(Sidebar sidebar) {
        Button play = makeUIButton(e -> runCommands(myCommandWindow.getText()));
        Image playImage = new Image(DEFAULT_IMAGE_PACKAGE + "PlayButton.jpg");
        ImageView playView = new ImageView(playImage);
        playView.setScaleX(.9);
        playView.setScaleY(.9);
        play.setGraphic(playView);
        sidebar.getSidebar().getChildren().add(play);
    }

    public void refresh (String s) {

    }

    private void addTurtle () {
        turtleList.add(new Turtle());
    }
    
}
