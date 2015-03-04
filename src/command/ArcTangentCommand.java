package command;


import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class ArcTangentCommand extends Command{

    public ArcTangentCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }

    @Override
    public double run(Turtle t) {
        return Math.atan(Math.toDegrees(parameters.get(0)));
    }


}
