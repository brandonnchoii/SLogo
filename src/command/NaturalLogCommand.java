package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class NaturalLogCommand extends Command{

    public NaturalLogCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }

    @Override
    public double run(Turtle t) {
        if(parameters.get(0) == 0)
            throw new IllegalArgumentException("Log zero");
        else
            return Math.log(parameters.get(0));
    }


}
