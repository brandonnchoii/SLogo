package world;

import parser.Parser;
import turtle.Turtle;

public abstract class World {

    private int height;
    private int width;
    private Turtle myTurtle;
    private Parser myParser;
    
    public World() {
        
    }
    
    public abstract void fixPosition();
    
    public void listen(String s) {
        
    }
    
    public void setHeight(int h){
        height = h;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setWidth(int w){
        width = w;
    }
    
    public int getWidth() {
        return width;
    }
}
