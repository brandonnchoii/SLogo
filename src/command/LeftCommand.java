package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class LeftCommand extends Command {

    public LeftCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }
    @Override
    public double run(Turtle t) {
        t.rotate(-1*parameters.get(0));
        return parameters.get(0);
    }

}
