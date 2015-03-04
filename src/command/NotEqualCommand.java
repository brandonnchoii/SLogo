package command;


import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class NotEqualCommand extends Command{

    public NotEqualCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }

    @Override
    public double run(Turtle t) {
        if(parameters.get(0) != parameters.get(1))
            return 1;
        return 0;
    }


}
