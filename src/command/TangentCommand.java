package command;


import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class TangentCommand extends Command{

    public TangentCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }

    @Override
    public double run(Turtle t) {
        return Math.tan(Math.toDegrees(parameters.get(0)));
    }


}
