package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class IDCommand extends Command {

    public IDCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }
    @Override
    public double run(Turtle t) {
        return (double) t.getID();
    }



}
