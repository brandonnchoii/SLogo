package command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import turtle.Turtle;

public abstract class Command {

    private static final String DEFAULT_START = "1";
    private static final String DEFAULT_END = "2";
    private static final String DEFAULT_INCR = "1";
    private static final String DEFAULT_VAR = ":repcount";
    protected List<Double> parameters;
    protected boolean loop;
    protected Map<String, String> commandValues;


    public Command(){
        parameters = new ArrayList<Double>();
        checkParams();
        loop = false;
        createCommandValues();
    }

    public Command(List<Double> params) {
        parameters = params;
        loop = false;
        createCommandValues();
    }

    public void addParam(double d){
        parameters.add(d);
    }

    protected void createCommandValues(){
        commandValues = new HashMap<>();
        commandValues.put("loopStart", DEFAULT_START);
        commandValues.put("loopEnd", DEFAULT_END);
        commandValues.put("loopIncrement", DEFAULT_INCR);
        commandValues.put("loopVariable", DEFAULT_VAR);
    }

    public abstract double run(Turtle t);

    public boolean isLoop(){
        return loop;
    }

    private void checkParams(){
        if (parameters.contains(null)){
            throw new IllegalArgumentException("The parameters are invalid"); 
        }
    }

    public Map<String, String> getCommandValues(Map<Integer,Turtle> turtles){
        commandValues.put("turtlesToAct", makeString(turtles));
        return commandValues;
    }

    protected String makeString(Map<Integer, Turtle> turtles){
        String s = "";
        for (Turtle t: turtles.values())
            if(t.isActive())
                s += t.getID() + " ";
        return s.trim();
    }

}
