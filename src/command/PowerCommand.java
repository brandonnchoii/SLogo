package command;

import java.util.List;
import turtle.Turtle;

public class PowerCommand extends Command{

    public PowerCommand(List<Double> params){
        super(params);
    }

    @Override
    public double run(Turtle t) {
        return Math.pow(parameters.get(0), parameters.get(1));
    }


}
