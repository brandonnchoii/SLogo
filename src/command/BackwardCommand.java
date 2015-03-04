package command;

import java.util.List;

import turtle.Turtle;

public class BackwardCommand extends Command {

    public BackwardCommand(List<Double> params){
        super(params);
    }
    @Override
    public double run(Turtle t) {
        for (Double d: parameters)
            t.move(-1*d);
        return parameters.get(0);
    }

}
