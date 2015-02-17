package command;

import java.util.ArrayList;

public abstract class Command {
    
    private ArrayList<Double> parameters;
    
    public Command() {
        
    }
    
    public abstract double run();
}
