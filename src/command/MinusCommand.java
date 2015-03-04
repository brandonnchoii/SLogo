package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class MinusCommand extends Command{

    public MinusCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }

    @Override
    public double run(Turtle t) {
        return -1*parameters.get(0);
    }
}
