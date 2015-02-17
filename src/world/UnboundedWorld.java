package world;

import turtle.Turtle;

public class UnboundedWorld extends World{

	public UnboundedWorld(){
		super();
	}
	
	public UnboundedWorld(int h, int w){
    	super(h,w);
    }
    
    public UnboundedWorld(int h, int w, Turtle t){
    	super(h,w,t);
    }
    

    @Override
    public void fixPosition () {
        myTurtle.fixPos(height, width);
    }

}
