package command;


import java.util.List;

import turtle.Turtle;

public class TangentCommand extends Command{

    public TangentCommand(List<Double> params){
        super(params);
    }

    @Override
    public double run(Turtle t) {
        return Math.tan(Math.toDegrees(parameters.get(0)));
    }


}
