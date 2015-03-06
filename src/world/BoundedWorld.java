package world;

import java.io.IOException;

import turtle.Turtle;

public class BoundedWorld extends World{

    public BoundedWorld() throws IOException {
        super();
    }

    public BoundedWorld(int h, int w) throws IOException{
        super(h,w);
    }

    public BoundedWorld(int h, int w, Turtle t, String l) throws IOException{
        super(h,w,t,l);
    }


    @Override
    public void fixPosition () {

    }

}
