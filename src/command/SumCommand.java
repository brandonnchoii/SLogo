package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class SumCommand extends Command{

    public SumCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }

    @Override
    public double run(Turtle t) {

        if(parameters.size() < 2)
            throw new IllegalArgumentException("Not enough parameters");

        double ret = 0;
        for(double d: parameters)
            ret += d;
        return ret;
    }
}
