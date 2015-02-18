package command;

import java.util.ArrayList;
import java.util.List;

import turtle.Turtle;

public abstract class Command {
    
    protected List<Double> parameters;
    
    public Command(List<Double> params) {
        parameters = params;
    }
    
    public abstract double run(Turtle t);
}
