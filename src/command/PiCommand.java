package command;


import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class PiCommand extends Command{

    public PiCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }

    @Override
    public double run(Turtle t) {
        return Math.PI;
    }


}
