/**
 * @author Brandon Choi, James Mosca
 */

package worldController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import turtle.Turtle;
import userInterface.LeftPanel;
import userInterface.RightPanel;
import userInterface.UI;
import userInterface.UIManager;
import world.BoundedWorld;
import world.UnboundedWorld;
import world.World;

public class WorldController {
    
//    public WorldController (StackPane pane) throws IOException {
//        myWorld = new BoundedWorld();
//        initializeController(pane);
//    }
//
//    public WorldController (StackPane pane, World w) {
//        myWorld = w;
//        initializeController(pane);
//    }
//
//    public WorldController (StackPane pane, boolean bounded) throws IOException  {
//        if (bounded)
//            myWorld = new BoundedWorld();
//        else 
//            myWorld = new UnboundedWorld();
//        initializeController(pane);
//    }
//    
//	private void initializeController(StackPane pane) {
//		myPane = pane;
//        myCanvas = (Canvas) pane.getChildren().get(0);
//        myGC = myCanvas.getGraphicsContext2D();
//        myTurtle = myWorld.getTurtle();
//        myCanvas.setWidth(myPane.getWidth());
//        myCanvas.setHeight(myPane.getHeight());
//        shift = new Point2D(myPane.getWidth() / 2.0, myPane.getHeight() / 2.0);
//        myPane.getChildren().add(myTurtle);
//        makeTimeline(PAUSE);
//	}
	
	private static final int CANVAS_INSET = 20;
	private static final int PAUSE = 10;
	
	World myWorld;
	private UI UI;
    private Point2D shift;
    private RightPanel myRightPanel;
    private LeftPanel myLeftPanel;
    private GraphicsContext myGC;
    private Timeline myAnimation;
    private Turtle myTurtle;
    private Canvas myCanvas;
    private StackPane myPane;
    
    private ObservableList<String> results;
    private ObservableList<String> previousCommands;
    private ObservableList<String> savedCommands;
    private ObservableMap<String, String> turtleMap;
    private ObservableMap<String, Double> variableMap;
    private StringProperty inputText;

    public WorldController (UI ui, RightPanel r, LeftPanel l, StringProperty s, List<ObjectProperty> bindings, StackPane pane) throws IOException {      
        myWorld = new BoundedWorld(bindings);
        
        setUpBindings(s);
        myRightPanel = r.getInstance();
        myRightPanel.initialize(results, previousCommands, savedCommands, inputText);
        myLeftPanel = l.getInstance();
        myLeftPanel.initialize(turtleMap, variableMap);
        
        UI = ui;
        myPane = pane;
        myCanvas = (Canvas) myPane.getChildren().get(0);
        myGC = myCanvas.getGraphicsContext2D();
        myTurtle = myWorld.getTurtle();
        myCanvas.setWidth(myPane.getWidth());
        myCanvas.setHeight(myPane.getHeight());
//        shiftX = myPane.getWidth() / 2.0;
//        shiftY = myPane.getHeight() / 2.0;
        myPane.getChildren().add(myTurtle);
        makeTimeline(PAUSE);
    }

    public WorldController (UI ui, World w, StackPane pane) {
        myWorld = w;
        UI = ui;
        initializeController(pane);

    }

    public WorldController (UI ui, boolean bounded, List<ObjectProperty> bindings, StackPane pane) throws IOException {
        if (bounded)
            myWorld = new BoundedWorld(bindings);
        else
            myWorld = new UnboundedWorld(bindings);
        UI = ui;
        initializeController(pane);
    }
  
	private void initializeController(StackPane pane) {
		myPane = pane;
		myCanvas = (Canvas) pane.getChildren().get(0);
        myGC = myCanvas.getGraphicsContext2D();
        myTurtle = myWorld.getTurtle();
        myCanvas.setWidth(myPane.getWidth());
        myCanvas.setHeight(myPane.getHeight());
        shift = new Point2D(myPane.getWidth() / 2.0, myPane.getHeight() / 2.0);
        myPane.getChildren().add(myTurtle);
        makeTimeline(PAUSE);
    }

    private void setUpBindings (StringProperty s) {
        results = FXCollections.observableArrayList();
        previousCommands = FXCollections.observableArrayList();
        savedCommands = FXCollections.observableArrayList();
        turtleMap = FXCollections.observableMap(new HashMap<String, String>());
        variableMap = FXCollections.observableMap(new HashMap<String, Double>());
        inputText = s;
    }

    private void makeTimeline (int framerate) {
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Animation.INDEFINITE);
        KeyFrame frame = makeKeyFrame(framerate, 0, 0);
        myAnimation.getKeyFrames().add(frame);
    }
     
     private KeyFrame makeKeyFrame(int framerate, double xTarget, double yTarget) {
    	 Duration t1 = new Duration(framerate);
    	 KeyValue kvx = new KeyValue(new SimpleDoubleProperty(xTarget), xTarget);
         KeyValue kvy = new KeyValue(new SimpleDoubleProperty(yTarget), yTarget);
         KeyFrame kf = new KeyFrame(t1,
          a -> {                          
        	  myTurtle.animatedMove(myGC, shift);
              Point2D next = myTurtle.nextFromCurrent();
              myAnimation.stop();
              myAnimation.getKeyFrames().remove(0);
              myAnimation.getKeyFrames().add(makeKeyFrame(
                   framerate, next.getX(), next.getY()));
              myAnimation.play();
          }, 
          kvx, kvy);
	      return kf;
     }
     
    public void update(String command) {
    	updateCanvasSize();
    	myTurtle.updatePenAttributes(myGC);
    	myAnimation.play();
        String s = myWorld.listen(command);
    	//UI.getSidebar().addResult(s);
    	//drawTurtle();
    }
    
    private void updateCanvasSize() {
    	myCanvas.setWidth(myPane.getWidth() - CANVAS_INSET);
    	myCanvas.setHeight(myPane.getHeight() - CANVAS_INSET);
        shift = new Point2D(myCanvas.getWidth() / 2.0, myCanvas.getHeight() / 2.0);
    }
    
    //resort to this method if the animator isn't working well
    //just uncomment drawTurtle() in the update method
    private void drawTurtle() {
        myTurtle.previousDrawLine(myGC, shift.getX(), shift.getY());
    }    
    
	public void clear () {
	
	}


    public World getWorld () {
        return myWorld;
    }
}
