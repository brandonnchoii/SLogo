package userInterface;

import java.io.IOException;
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
import javafx.scene.layout.StackPane;
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
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
    private static final String DEFAULT_IMAGE_PACKAGE = "resources/images/";
    private static final String[] buttons = { "ChangeTurtleCommand", "RunCommand", "SaveCommand" };

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
    private StackPane canvasPane;
    private Button commandSaverButton;
    
    public UserInterface () throws IOException {
        myRoot = new BorderPane();
        myRoot.setPadding(new Insets(15, 20, 15, 20));
        mySidebar = new Sidebar();
        makeCreateTurtle();
        makeSaveCommand();
        myTopbar = new Topbar();
        //myController = new WorldController(this);
        // addTurtle();
        setupPane();
        makeTimeline();
        //myController = new WorldController(this);
        myMenuNames = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");

        myCommandWindow = makeCommandWindow();

        //setupPane();
        myScene = new Scene(myRoot, myWidth, myHeight);
        makeFunctionButtons();
        setUpResponses();
        myRoot.setBottom(myCommandWindow);
        myRoot.setRight(mySidebar.getSidebar());
        myRoot.setTop(myTopbar.getTopbar());
    }

    public Scene getScene () {
        return myScene;
    }

    private void setUpResponses () {
        // change background color
        myTopbar.myColorChoices.setOnAction(e -> {
            canvasPane.setStyle("-fx-background-color: " + "#" +
                                myTopbar.myColorChoices.getValue().toString().substring(2));
        });

        // change language choice
        myTopbar.languageChoices.setOnAction(e -> {

        });

        // save command
        commandSaverButton.setOnMouseClicked(e -> {
            CommandSaver cs = new CommandSaver();
            cs.saveCommand();
            cs.saveButton.setOnMouseClicked(c -> {
                mySidebar.addItem(cs.commandWindow.getText().replaceAll("\n", " , "), Sidebar.SAVED_INDEX);
                cs.close();
            });
        });

        // create new turtle
        TurtleMaker tm = new TurtleMaker();
        
        //access specific saved command
        mySidebar.boxes.get(Sidebar.SAVED_INDEX).setOnAction(e -> {
            runCommand(mySidebar.boxes.get(Sidebar.SAVED_INDEX).getSelectionModel().getSelectedItem());
        });
        
        //access specific previous command
        
        //access specific turtle 
        
        //edit specific turtle

    }

    private void setupPane () {
        canvasPane = new StackPane();
        canvasPane.setPadding(new Insets(10, 10, 10, 10));
        myRoot.setCenter(canvasPane);
        myCanvas = new Canvas();
    	myGC = myCanvas.getGraphicsContext2D();
        canvasPane.getChildren().add(myCanvas);
        canvasPane.setStyle("-fx-background-color: white");
    }
    
    public void setUpController() throws IOException {
    	myController = new WorldController(this);
    }

    private TextArea makeCommandWindow () {
        TextArea commandWindow = new TextArea("Enter SLogo command(s) HERE.");
        commandWindow.setOnMouseClicked(e -> commandWindow.clear());
        return commandWindow;
    }

    private void makeFunctionButtons () {
        makeSwitchTurtle();
        makeRunButton();
    }

    private void makeSwitchTurtle () {
        Button save = makeUIButton("ChangeTurtleCommand", e -> changeTurtle());
        mySidebar.add(save);
    }

    private void addTurtle () {
        // TODO Auto-generated method stub
    }

    // open file chooser for turtle image
    // need to make a default (probably xml)
    private void changeTurtle () {

    }

    private void saveCommand (String s) {

    }

    private void runCommand (String data) {
    	myController.update(data);
    }

    private void makeCreateTurtle () {
        Button turtleCreate = new Button("Create New Turtle");
        turtleCreate.setMinWidth(BOX_WIDTH);
        turtleCreate.setOnMouseClicked(e -> new TurtleMaker());
        mySidebar.add(turtleCreate);
    }

    private void makeSaveCommand () {
        commandSaverButton = new Button("Save New Command");
        commandSaverButton.setMinWidth(BOX_WIDTH);
        mySidebar.add(commandSaverButton);
    }

    private void makeTimeline () {
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

    private void makeRunButton () {
        Button play = makeUIButton("RunCommand", e -> runCommand(myCommandWindow.getText()));
        Image playImage = new Image(DEFAULT_IMAGE_PACKAGE + "PlayButton.jpg");
        ImageView playView = new ImageView(playImage);
        playView.setFitWidth(60);
        playView.setFitHeight(40);
        play.setGraphic(playView);
        mySidebar.add(play);
    }

    public void refresh (String s) {

    }

    public GraphicsContext getGraphics() {
    	return myGC;
    }

    public Timeline getTimeline() {
    	return myAnimation;
    }
    
    public Canvas getCanvas() {
    	return myCanvas;
    }
    
    public StackPane getPane() {
    	return canvasPane;
    }
    
}
