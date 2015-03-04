package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class SineCommand extends Command{

    public SineCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }

    @Override
    public double run(Turtle t) {
        return Math.sin(Math.toRadians(parameters.get(0)));
    }
}
