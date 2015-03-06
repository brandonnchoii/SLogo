package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class IsShowingCommand extends Command{

    public IsShowingCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }

    @Override
    public double run(Turtle t) {
        if(t.isVisible())
            return 1;
        return 0;
    }
}
