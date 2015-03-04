package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class CosineCommand extends Command{

    public CosineCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }

    @Override
    public double run(Turtle t) {
        return Math.cos(Math.toRadians(parameters.get(0)));
    }
}
