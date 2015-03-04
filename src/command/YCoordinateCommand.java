package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class YCoordinateCommand extends Command{

    public YCoordinateCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }

    @Override
    public double run(Turtle t) {
        return t.getTranslateY();
    }
}
