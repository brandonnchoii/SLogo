package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class PenDownCommand extends Command {

    public PenDownCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }
    @Override
    public double run(Turtle t) {
        t.setDrawing(true);
        return 1;
    }

}
