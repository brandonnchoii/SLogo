package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class PowerCommand extends Command{

    public PowerCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }

    @Override
    public double run(Turtle t) {
        return Math.pow(parameters.get(0), parameters.get(1));
    }


}
