package userInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import turtle.Turtle;
import world.World;
import worldController.WorldController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import javafx.util.Duration;

public class UserInterface {
    
    //private static UserInterface instance;
    private static final int myWidth = 500;
    private static final int myHeight = 500;    
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/properties/";
    
    private World myWorld;
    private WorldController myController;
    private List<Turtle> turtleList;
    //private HBox commandField;
    private VBox mySideBar;
    private Scene myScene;
    private Group myRoot;
    private ResourceBundle myMenuNames;
    private TextArea myCommandWindow;
    private Timeline myAnimation;
    
    public UserInterface() {
    	myRoot = new Group();
    	turtleList = new ArrayList<Turtle>();
    	//addTurtle();
    	myMenuNames = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE
				+ "UIMenu");
    	mySideBar = makeSideBar();
    	myCommandWindow = makeCommandWindow();
    	makeTimeline();
    	myRoot.getChildren().addAll(mySideBar, myCommandWindow);
    	myScene = new Scene(myRoot);
    }
    
    private void runCommands(String s) {
    	String data = s;
    	myController.update();
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
    
    private VBox makeSideBar() {
		VBox verticalBar = new VBox(10);
		Button turtle = makeTurtleButton();
		Button run = makeRunButton();
		verticalBar.getChildren().addAll(run, turtle);
		verticalBar.setTranslateX(myWidth);
		return verticalBar;
	}
    
    private TextArea makeCommandWindow() {
		TextArea command = new TextArea();
		command.setTranslateY(myHeight);
		return command;
	}
    
    private Button makeTurtleButton() {
    	return makeUIButton("ChangeTurtleCommand", e -> changeTurtle());
    }
    
    private Button makeRunButton() {
    	return makeUIButton("RunCommand", 
    			e -> runCommands(myCommandWindow.getText()));
    }
    
    private Button saveCommandButton() {
    	return makeUIButton("SaveCommand",
    			e -> saveCommand(myCommandWindow.getText()));
    }
    
    //open file chooser for turtle image
    //need to make a default (probably xml)
	private void changeTurtle() {
		
	}

	private void saveCommand(String s) {
		//this is going to take some effort
		//we need to wait til we can make commands
		//then add commands of these strings to an ArrayList
	}
	
	private Button makeUIButton(String name, EventHandler<MouseEvent> e) {
		Button test = new Button(myMenuNames.getString(name));
		test.setMaxWidth(Double.MAX_VALUE);
		test.setOnMousePressed(e);
		return test;
	}
    
    public Scene getScene() {
    	return myScene;
    }
    
    public void refresh(String s) {
        
    }
  
    private void addTurtle() {
    	turtleList.add(new Turtle());
    }

    private void makeTimeline() {
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Animation.INDEFINITE);
		myAnimation.play();
	}

}
