package command;

import java.util.List;

import turtle.Turtle;

public class SetHeadingCommand extends Command {

    public SetHeadingCommand(List<Double> params){
        super(params);
    }
    @Override
    public double run(Turtle t) {
        double pastDir = t.getDirection();
        t.setHeading(parameters.get(0));
        return Math.abs(parameters.get(0)-pastDir);
    }

}
