package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class HideTurtleCommand extends Command {

    public HideTurtleCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }
    @Override
    public double run(Turtle t) {
        t.setVisible(false);
        return 0;
    }

}
