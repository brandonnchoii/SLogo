package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class PenDownCommand extends Command {

    public PenDownCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }
    @Override
    public double run(Turtle t) {
        t.setDrawing(parameters.get(0) != 0);
        return parameters.get(0);
    }

}
