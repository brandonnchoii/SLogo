/**
* 
* @author Brandon Choi, James Mosca
*
*/

package worldController;

import java.io.IOException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import turtle.Turtle;
import userInterface.UserInterface;
import world.BoundedWorld;
import world.UnboundedWorld;
import world.World;

public class WorldController {

    World myWorld;
    private UserInterface UI;
    private GraphicsContext myGC;
    private Turtle myTurtle;
    
    public WorldController (UserInterface ui) throws IOException {
        myWorld = new BoundedWorld();
        UI = ui;
        myGC = UI.getGraphics();
        myTurtle = myWorld.getTurtle();
        myTurtle.setFitHeight(40);
        myTurtle.setFitWidth(40);
        myGC.drawImage(myTurtle.getImage(), 100, 100);
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
        myTurtle.setFitHeight(40);
        myTurtle.setFitWidth(40);
    }
    public void update(String command) {
    	myWorld.listen(command);
    }

    public void clear () {

    }

    public World getWorld () {
        return myWorld;
    }


}
