package turtle;

import java.util.Queue;

import javafx.scene.Group;
import javafx.scene.image.Image;
import command.Command;

public class Turtle {

    private boolean pen;
    private boolean visible;
    private int xC;
    private int yC;
    private double direction;
    private Queue<Command> myCommands;
    private Image myImage;
    
    public Turtle() {
        
    }
    
    public void drawSelf(Group g){
        
    }
    
    public boolean isDrawing() {
        return pen;
    }
    
    public boolean isVisible() {
        return visible;
    }
    
    public void setDrawing(boolean b){
        pen = b;
    }
    
    public void setVisible(boolean b){
        visible = b;
    }
    
    public int getX() {
        return xC;
    }
    
    public int getY() {
        return yC;
    }
    
    public void act() {
        
    }
    
    public void move (int pixels) {
        
    }
    
    public void rotate (double degrees) {
        
    }
    
    public void setHeading (double degrees) {
        
    }
    
    public void addCommands(Queue<Command> commands){
        myCommands = commands;
    }
}
