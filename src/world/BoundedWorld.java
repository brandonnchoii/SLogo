package world;

import java.io.IOException;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import turtle.Turtle;

public class BoundedWorld extends World{
	
	public BoundedWorld(List<ObjectProperty> bindings) throws IOException {
		super(bindings);
	}
	
	public BoundedWorld(List<ObjectProperty> bindings, int h, int w) throws IOException{
    	super(bindings, h,w);
    }
    
    public BoundedWorld(List<ObjectProperty> bindings, int h, int w, Turtle t, String l) throws IOException{
    	super(bindings, h,w,t,l);
    }
    

    @Override
    public void fixPosition () {
        
    }

}
