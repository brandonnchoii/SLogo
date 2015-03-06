package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class ShowTurtleCommand extends Command {

    public ShowTurtleCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }
    @Override
    public double run(Turtle t) {
        t.setVisible(parameters.get(0) != 0);
        return parameters.get(0);
    }

}
