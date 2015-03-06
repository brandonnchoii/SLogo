package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class PenUpCommand extends Command {

    public PenUpCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }
    @Override
    public double run(Turtle t) {
        t.setDrawing(false);
        return 0;
    }

}
