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

    public WorldController (boolean bounded) throws IOException {
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
