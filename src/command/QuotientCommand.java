package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class QuotientCommand extends Command{

    public QuotientCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }

    @Override
    public double run(Turtle t) {

        if(parameters.size() < 2)
            throw new IllegalArgumentException("Not enough parameters");
        double ret = parameters.get(0);
        for(int i = 1; i < parameters.size();i++){
            if(parameters.get(i) == 0)
                throw new IllegalArgumentException("Divide by zero");
            ret /= parameters.get(i);
        }

        return ret;
    }
}
