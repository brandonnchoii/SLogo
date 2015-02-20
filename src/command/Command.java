package command;

import java.util.ArrayList;
import java.util.List;

import turtle.Turtle;

public abstract class Command {
    
    protected List<Double> parameters;
    
    public Command(){
    	parameters = new ArrayList<Double>();
    }
    public Command(List<Double> params) {
        parameters = params;
    }
    
    public void addParam(double d){
    	parameters.add(d);
    }
    public abstract double run(Turtle t);
}
