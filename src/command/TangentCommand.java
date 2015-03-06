package command;


import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class TangentCommand extends Command{

    public TangentCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }

    @Override
    public double run(Turtle t) {
        return Math.tan(Math.toDegrees(parameters.get(0)));
    }


}
