package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class DifferenceCommand extends Command{

    public DifferenceCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }

    @Override
    public double run(Turtle t) {

        if(parameters.size() < 2)
            throw new IllegalArgumentException("Not enough parameters");

        double ret = parameters.get(0);
        for (int i = 1; i < parameters.size(); i++){
            ret -= parameters.get(i);
        }
        return ret;
    }
}
