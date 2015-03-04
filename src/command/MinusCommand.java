package command;

import java.util.List;

import turtle.Turtle;

public class MinusCommand extends Command{

    public MinusCommand(List<Double> params){
        super(params);
    }

    @Override
    public double run(Turtle t) {
        return -1*parameters.get(0);
    }
}
