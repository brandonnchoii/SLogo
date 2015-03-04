package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class ForwardCommand extends Command {

    public ForwardCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }
    
    @Override
    public double run(Turtle t) {
        for (Double d: parameters)
            t.move(d);
        return parameters.get(0);
    }

}
