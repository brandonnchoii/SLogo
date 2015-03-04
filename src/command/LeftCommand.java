package command;

import java.util.List;

import turtle.Turtle;

public class LeftCommand extends Command {

    public LeftCommand(List<Double> params){
        super(params);
    }
    @Override
    public double run(Turtle t) {
        t.rotate(-1*parameters.get(0));
        return parameters.get(0);
    }

}
