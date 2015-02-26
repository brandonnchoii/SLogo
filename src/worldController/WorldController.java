/**
* 
* @author Brandon Choi, James Mosca
*
*/

package worldController;

import java.io.IOException;

import world.BoundedWorld;
import world.UnboundedWorld;
import world.World;

public class WorldController {

    World myWorld;

    public WorldController () throws IOException {
        myWorld = new BoundedWorld();
    }

    public WorldController (World w) {
        myWorld = w;
    }

    public WorldController (boolean bounded) throws IOException  {
        if (bounded)
            myWorld = new BoundedWorld();
        else
            myWorld = new UnboundedWorld();
    }

    public void update(String command) {
        //System.out.println(command);
    	myWorld.listen(command);
    }

    public void clear () {

    }

    public World getWorld () {
        return myWorld;
    }
}
