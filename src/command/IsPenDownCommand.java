package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class IsPenDownCommand extends Command{

    public IsPenDownCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }

    @Override
    public double run(Turtle t) {
        if(t.isDrawing())
            return 1;
        return 0;
    }
}
