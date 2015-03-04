package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class RightCommand extends Command {

    public RightCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }
    @Override
    public double run(Turtle t) {
        t.rotate(parameters.get(0));
        return parameters.get(0);
    }

}
