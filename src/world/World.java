package world;

import parser.Parser;
import turtle.Turtle;

public abstract class World {

    protected int height;
    protected int width;
    protected Turtle myTurtle;
    private Parser myParser;
    
    private static final int DEFAULT_HEIGHT = 100;
    private static final int DEFAULT_WIDTH = 100;
    
    public World() {
        height = DEFAULT_HEIGHT;
        width = DEFAULT_WIDTH;
        myTurtle = new Turtle();
        myParser = new Parser();
    }
    
    public World(int h, int w){
    	height = h;
    	width = w;
    	myTurtle = new Turtle();
    	myParser = new Parser();
    }
    
    public World(int h, int w, Turtle t){
    	height = h;
    	width = w;
    	myTurtle = t;
    	myParser = new Parser();
    }
    
    public abstract void fixPosition();
    
    public void listen(String s) {
        myTurtle.act(myParser.parse(s));
       
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
