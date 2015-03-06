package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class RandomCommand extends Command{

    public RandomCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }

    @Override
    public double run(Turtle t) {
        return parameters.get(0) * Math.random();
    }
}
