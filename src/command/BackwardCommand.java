package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class BackwardCommand extends Command {

    public BackwardCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }
    @Override
    public double run(Turtle t) {
        for (Double d: parameters)
            t.move(-1*d);
        return parameters.get(0);
    }

}
