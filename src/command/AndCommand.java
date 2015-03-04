package command;


import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class AndCommand extends Command{


    public AndCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }

    @Override
    public double run(Turtle t) {

        if(parameters.get(0) != 0 && parameters.get(1) != 0)
            return 1;
        return 0;
    }


}
