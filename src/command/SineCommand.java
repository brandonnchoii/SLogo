package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class SineCommand extends Command{

    public SineCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }

    @Override
    public double run(Turtle t) {
        return Math.sin(Math.toRadians(parameters.get(0)));
    }
}
