package command;


import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class PiCommand extends Command{

    public PiCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }

    @Override
    public double run(Turtle t) {
        return Math.PI;
    }


}
