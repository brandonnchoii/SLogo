package command;

import java.util.List;
import turtle.Turtle;

public class NaturalLogCommand extends Command{

    public NaturalLogCommand(List<Double> params){
        super(params);
    }

    @Override
    public double run(Turtle t) {
        if(parameters.get(0) == 0)
            throw new IllegalArgumentException("Log zero");
        else
            return Math.log(parameters.get(0));
    }


}
