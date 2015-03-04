package command;

import java.util.List;

import turtle.Turtle;

public class PenDownCommand extends Command {

    public PenDownCommand(List<Double> params){
        super(params);
    }
    @Override
    public double run(Turtle t) {
        t.setDrawing(parameters.get(0) != 0);
        return parameters.get(0);
    }

}
