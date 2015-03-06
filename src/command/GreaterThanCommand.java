package command;


import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class GreaterThanCommand extends Command{

    public GreaterThanCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }

    @Override
    public double run(Turtle t) {
        if(parameters.get(0) > parameters.get(1))
            return 1;
        return 0;
    }


}
