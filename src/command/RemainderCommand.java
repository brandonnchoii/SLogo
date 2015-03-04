package command;

import java.util.List;

import turtle.Turtle;

public class RemainderCommand extends Command {

    public RemainderCommand(List<Double> params){
        super(params);
    }
    @Override
    public double run(Turtle t) {
        if(parameters.get(1) == 0)
            throw new IllegalArgumentException("Divide by zero");
        else
            return parameters.get(0) % parameters.get(1);
    }

}
