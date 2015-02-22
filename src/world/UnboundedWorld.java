package world;

import java.io.IOException;

import turtle.Turtle;

public class UnboundedWorld extends World{

	public UnboundedWorld() throws IOException{
		super();
	}
	
	public UnboundedWorld(int h, int w) throws IOException{
    	super(h,w);
    }
    
    public UnboundedWorld(int h, int w, Turtle t, String l) throws IOException{
    	super(h,w,t,l);
    }
    

    @Override
    public void fixPosition () {
        myTurtle.fixPos(height, width);
    }

}
