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
import javafx.scene.Group;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class UserInterface {

    // private static UserInterface instance;
    private static final int myWidth = 800;
    private static final int myHeight = 700;
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
    private static final String DEFAULT_IMAGE_PACKAGE = "resources/images/";
    
    private WorldController myController;
    private List<Turtle> turtleList;
    //private HBox commandField;

    private VBox mySidebar;
    private Scene myScene;
    private BorderPane myRoot;
    private ResourceBundle myMenuNames;
    private TextArea myCommandWindow;
    private Timeline myAnimation;
    private Canvas myCanvas;
    private GraphicsContext myGC;
    
    public UserInterface () {
        myRoot = new BorderPane();
        myRoot.setPadding(new Insets(15, 20, 15, 20));
        turtleList = new ArrayList<Turtle>();
        myCanvas = new Canvas(300, 300);
        myGC = myCanvas.getGraphicsContext2D();
        myMenuNames = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
        mySidebar = makeSidebar();
        myCommandWindow = makeCommandWindow();
        changeColor(Color.RED);
        myGC.fillRect(100, 100, 50, 50);
        
        makeTimeline();
        setupPane();
        myScene = new Scene(myRoot, myWidth, myHeight, Color.BLACK);
    }
    /**
     * Implement Singleton OO Design architecture
     * do we need to this even if we are making a single case of it from main only anyway?
     */
    /*public static synchronized UserInterface getInstance() {
        if (instance == null)
            instance = new UserInterface(myWidth, myHeight);
        
        return instance;
    }*/
    
    private void setupPane() {
    	StackPane p = new StackPane();
        myRoot.setCenter(p);
    	p.setStyle("-fx-background-color: blue");
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

    // open file chooser for turtle image
    // need to make a default (probably xml)
    private void changeTurtle () {

    }
    
    private void runCommands (String s) {
        String data = s;
        myController.update();
    }

    private void saveCommand (String s) {
        // this is going to take some effort
        // we need to wait til we can make commands
        // then add commands of these strings to an ArrayList
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
    
    public Scene getScene() {
    	return myScene;
    }

}
