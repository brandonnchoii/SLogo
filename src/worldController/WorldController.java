/**
* 
* @author Brandon Choi, James Mosca
*
*/

package worldController;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import turtle.Pen;
import turtle.Turtle;
import userInterface.UserInterface;
import world.BoundedWorld;
import world.UnboundedWorld;
import world.World;

public class WorldController {

	private static final int PAUSE = 10;
	
	World myWorld;
    private UserInterface UI;
    private GraphicsContext myGC;
    private Timeline myAnimation;
    private Turtle myTurtle;
    private Canvas myCanvas;
    private StackPane myPane;
    private Point2D shift;
    
    public WorldController (UserInterface ui) throws IOException {
        myWorld = new BoundedWorld();
        UI = ui;
        myCanvas = UI.getCanvas();
        myPane = UI.getPane();
        myGC = UI.getGraphics();
        myTurtle = myWorld.getTurtle();
        myCanvas.setWidth(myPane.getWidth());
        myCanvas.setHeight(myPane.getHeight());
        shift = new Point2D(myPane.getWidth() / 2.0, myPane.getHeight() / 2.0);
        myPane.getChildren().add(myTurtle);
        makeTimeline(PAUSE);
    }

    public WorldController (UserInterface ui, World w) {
        myWorld = w;
        UI = ui;
        myGC = UI.getGraphics();
        myTurtle = myWorld.getTurtle();

    }

    public WorldController (UserInterface ui, boolean bounded) throws IOException  {
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

     
     private KeyFrame makeKeyFrame(int framerate, double xTarget, double yTarget) {
    	 Duration t1 = new Duration(framerate);
    	 DoubleProperty xProp = new SimpleDoubleProperty(xTarget);
    	 DoubleProperty yProp = new SimpleDoubleProperty(yTarget);
    	 KeyValue kvx = new KeyValue(xProp, xTarget);
         KeyValue kvy = new KeyValue(yProp, yTarget);
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
    	myTurtle.updatePenAttributes(myGC);
    	myAnimation.play();
        String s = myWorld.listen(command);
    	UI.getSidebar().addResult(s);
    	//drawTurtle();
    }
    
    //resort to this method if the animator isn't working well
    //just uncomment drawTurtle() in the update method
    private void drawTurtle() {
        myTurtle.previousDrawLine(myGC, shift.getX(), shift.getY());
    }    
    
	public void clear () {

    }

}
