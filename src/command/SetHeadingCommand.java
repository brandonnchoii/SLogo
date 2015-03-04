package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class SetHeadingCommand extends Command {

    public SetHeadingCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);

    }
    @Override
    public double run(Turtle t) {
        double pastDir = t.getDirection();
        t.setHeading(parameters.get(0));
        return Math.abs(parameters.get(0)-pastDir);
    }

}
