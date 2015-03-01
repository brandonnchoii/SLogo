/**
* 
* @author Brandon Choi, James Mosca
*
*/

package worldController;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import turtle.Pen;
import turtle.Turtle;
import userInterface.UserInterface;
import world.BoundedWorld;
import world.UnboundedWorld;
import world.World;

public class WorldController {

	World myWorld;
    private UserInterface UI;
    private GraphicsContext myGC;
    private Timeline myAnimation;
    private Turtle myTurtle;
    private Canvas myCanvas;
    private StackPane myPane;
    private double shiftX;
    private double shiftY;
    
    public WorldController (UserInterface ui) throws IOException {
        myWorld = new BoundedWorld();
        UI = ui;
        myCanvas = UI.getCanvas();
        myPane = UI.getPane();
        myGC = UI.getGraphics();
        myAnimation = UI.getTimeline();
        myTurtle = myWorld.getTurtle();
        myCanvas.setWidth(myPane.getWidth());
        myCanvas.setHeight(myPane.getHeight());
        shiftX = myPane.getWidth() / 2.0;
        shiftY = myPane.getHeight() /2.0;
        myPane.getChildren().add(myTurtle);
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
    
    public void update(String command) {
        System.out.println(command + "wc");
    	String s = myWorld.listen(command);
    	UI.getSidebar().addResult(s);
    	drawTurtle();
    }

    private void drawTurtle() {
        myTurtle.drawLine(myGC, shiftX, shiftY);
    }

	public void clear () {

    }

    public World getWorld () {
        return myWorld;
    }


}
