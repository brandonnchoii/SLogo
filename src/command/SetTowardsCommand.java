package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class SetTowardsCommand extends Command {

    public SetTowardsCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }
    @Override
    public double run(Turtle t) {
        double pastDir = t.getDirection();
        double newDir = Math.toDegrees(Math.atan2(parameters.get(1), parameters.get(0)));
        t.setHeading(newDir);
        return pastDir - newDir;
    }


}
