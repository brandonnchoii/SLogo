package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class IsPenDownCommand extends Command{

    public IsPenDownCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }

    @Override
    public double run(Turtle t) {
        if(t.isDrawing())
            return 1;
        return 0;
    }
}
