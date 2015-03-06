package command;


import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class NotCommand extends Command{


    public NotCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }

    @Override
    public double run(Turtle t) {
        if(parameters.get(0) == 0)
            return 1;
        return 0;
    }


}
