package world;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import command.Command;
import command.DoTimesCommand;
import command.ForCommand;
import command.IfCommand;
import command.LoopCommand;
import command.RepeatCommand;
import javafx.scene.paint.Color;
import parser.Parser;
import turtle.Turtle;

public abstract class World {

    protected int height;
    protected int width;
  //Turtle extends IV but eventaully, we want to not give WC or UI the entire Turtle
    protected Turtle myTurtle;
    private Map<Double, String> loopMap;
    private List<Class> loopList;
    private Parser myParser;
    
    private static final int DEFAULT_HEIGHT = 100;
    private static final int DEFAULT_WIDTH = 100;
    private static final Color TURTLE_DEFAULT = Color.BLACK;
    
    
    public World() throws IOException {
        height = DEFAULT_HEIGHT;
        width = DEFAULT_WIDTH;

        myTurtle = new Turtle(TURTLE_DEFAULT);
        myParser = new Parser("English");
        makeLoops();
    }
    
    public World(int h, int w) throws IOException{
    	height = h;
    	width = w;
    	myTurtle = new Turtle(TURTLE_DEFAULT);
    	myParser = new Parser("English");
    	makeLoops();
    }
    
    public World(int h, int w, Turtle t, String language) throws IOException{
    	height = h;
    	width = w;
    	myTurtle = t;
    	myParser = new Parser(language);
    	makeLoops();
    }
    
    private void makeLoops(){
    	makeLoopMap();
    	makeLoopList();
    }
    
    private void makeLoopMap(){
    	loopMap = new HashMap<>();
    	loopMap.put(0., "Loop");
    	loopMap.put(1., "Done");
    }
    
    private void makeLoopList(){
    	loopList = new ArrayList<>();
    	loopList.add(new LoopCommand().getClass());
    	loopList.add(new IfCommand().getClass());
    }
    public abstract void fixPosition();
    	
    public void listen(String s) {
    	int numCmds = myParser.initializeCommands(s);
    	String param = "";
    	for (int i = 0; i < numCmds; i++)
    		param = runCommand(param);    
    	
    }
    
    private String runCommand(String s){
    	Command c = myParser.parse(s);
    	if(isLoop(c)){
    		return loopMap.get(myTurtle.act(c));
    	}
    	return myTurtle.act(c);
    	
    }
    
    private boolean isLoop(Command c){
    	if(loopList.contains(c.getClass()))
    		return true;
    	return false;
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
    
    public Turtle getTurtle() {
    	return myTurtle;
    }
}
