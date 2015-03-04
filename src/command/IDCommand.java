package command;

import java.util.List;

import turtle.Turtle;

public class IDCommand extends Command {

    public IDCommand(List<Double> params){
        super(params);
    }
    @Override
    public double run(Turtle t) {
        return (double) t.getID();
    }



}
