package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class PenUpCommand extends Command {

    public PenUpCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }
    @Override
    public double run(Turtle t) {
        t.setDrawing(false);
        return 0;
    }

}
