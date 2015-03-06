package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class PowerCommand extends Command{

    public PowerCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }

    @Override
    public double run(Turtle t) {
        return Math.pow(parameters.get(0), parameters.get(1));
    }


}
