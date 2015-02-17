package world;

import turtle.Turtle;

public class BoundedWorld extends World{
	
	public BoundedWorld(){
		super();
	}
	
	public BoundedWorld(int h, int w){
    	super(h,w);
    }
    
    public BoundedWorld(int h, int w, Turtle t){
    	super(h,w,t);
    }
    

    @Override
    public void fixPosition () {
        
    }

}
