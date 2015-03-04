package command;


import java.util.List;

import turtle.Turtle;

public class PiCommand extends Command{

    public PiCommand(List<Double> params){
        super(params);
    }

    @Override
    public double run(Turtle t) {
        return Math.PI;
    }


}
