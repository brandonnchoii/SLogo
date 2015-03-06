/**
 * 
 * @author Brandon Choi, James Mosca
 *
 */

package worldController;

import java.io.IOException;
import java.util.HashMap;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import turtle.Pen;
import turtle.Turtle;
import userInterface.LeftPanel;
import userInterface.RightPanel;
import userInterface.UI;
import world.BoundedWorld;
import world.UnboundedWorld;
import world.World;

public class WorldController {

    private static final int PAUSE = 10;

    World myWorld;
    private UI UI;
    private GraphicsContext myGC;
    private Timeline myAnimation;
    private Turtle myTurtle;
    private Canvas myCanvas;
    private StackPane myPane;
    private double shiftX;
    private double shiftY;
    private RightPanel myRightPanel;
    private LeftPanel myLeftPanel;
    
    private ObservableList<String> results;
    private ObservableList<String> previousCommands;
    private ObservableList<String> savedCommands;
    private ObservableMap<String, String> turtleMap;
    private ObservableMap<String, Double> variableMap;

    public WorldController (UI ui, RightPanel r, LeftPanel l) throws IOException {
        
        results = FXCollections.observableArrayList();
        previousCommands = FXCollections.observableArrayList();
        savedCommands = FXCollections.observableArrayList();
        turtleMap = FXCollections.observableMap(new HashMap<String, String>());
        variableMap = FXCollections.observableMap(new HashMap<String, Double>());
              
        myRightPanel = r.getInstance();
        myRightPanel.initialize(results, previousCommands, savedCommands);
        
        myLeftPanel = l.getInstance();
        myLeftPanel.initialize(turtleMap, variableMap);
        
         
        myWorld = new BoundedWorld();
        UI = ui;
        myCanvas = UI.getCanvas();
        myPane = UI.getPane();
        myGC = UI.getGraphics();
        myTurtle = myWorld.getTurtle();
        myCanvas.setWidth(myPane.getWidth());
        myCanvas.setHeight(myPane.getHeight());
        shiftX = myPane.getWidth() / 2.0;
        shiftY = myPane.getHeight() / 2.0;
        myPane.getChildren().add(myTurtle);
        makeTimeline(PAUSE);
    }

    public WorldController (UI ui, World w) {
        myWorld = w;
        UI = ui;
        myGC = UI.getGraphics();
        myTurtle = myWorld.getTurtle();

    }

    public WorldController (UI ui, boolean bounded) throws IOException {
        if (bounded)
            myWorld = new BoundedWorld();
        else
            myWorld = new UnboundedWorld();
        UI = ui;
        myGC = UI.getGraphics();
        myTurtle = myWorld.getTurtle();
    }

    private void makeTimeline (int framerate) {
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Animation.INDEFINITE);
        KeyFrame frame = makeKeyFrame(framerate, 0, 0);
        myAnimation.getKeyFrames().add(frame);
    }

    private KeyFrame makeKeyFrame (int framerate, double xTarget, double yTarget) {
        Point2D current = myTurtle.getCurrent();
        Point2D goal = myTurtle.getGoal();
        Duration t1 = new Duration(framerate);
        DoubleProperty xProp = new SimpleDoubleProperty(xTarget);
        DoubleProperty yProp = new SimpleDoubleProperty(yTarget);
        KeyValue kv0 = new KeyValue(xProp, xTarget);
        KeyValue kv1 = new KeyValue(yProp, yTarget);
        KeyFrame kf = new KeyFrame(t1, a -> {
            myTurtle.animatedMove(myGC, shiftX, shiftY);
            Point2D next = myTurtle.findNextPoint(current.getX(), current.getY(), goal.getX(),
                                                  goal.getY());
            double nextXGoal = next.getX();
            double nextYGoal = next.getY();
            myAnimation.stop();
            myAnimation.getKeyFrames().remove(0);
            myAnimation.getKeyFrames().add(makeKeyFrame(framerate, nextXGoal, nextYGoal));
            myAnimation.play();
        }, kv0, kv1);
        return kf;
    }

    public void update (String command) {
        myTurtle.updatePenAttributes(myGC);
        myAnimation.play();
        results.add(command + " = " + myWorld.listen(command));
        previousCommands.add(command);
        //drawTurtle();
    }

    // resort to this method if the animator isn't working well
    // just uncomment drawTurtle() in the update method
    private void drawTurtle () {
        myTurtle.previousDrawLine(myGC, shiftX, shiftY);
    }

    public void clear () {

    }

    public World getWorld () {
        return myWorld;
    }
}