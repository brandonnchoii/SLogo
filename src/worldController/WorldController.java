package worldController;

import world.BoundedWorld;
import world.UnboundedWorld;
import world.World;

public class WorldController {

    World myWorld;

    public WorldController () {
        myWorld = new BoundedWorld();
    }

    public WorldController (World w) {
        myWorld = w;
    }

    public WorldController (boolean bounded) {
        if (bounded)
            myWorld = new BoundedWorld();
        else
            myWorld = new UnboundedWorld();
    }

    public void update () {

    }

    public void clear () {

    }

    public World getWorld () {
        return myWorld;
    }
}
