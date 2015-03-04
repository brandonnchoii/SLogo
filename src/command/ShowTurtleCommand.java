package command;

import java.util.List;

import turtle.Turtle;

public class ShowTurtleCommand extends Command {

    public ShowTurtleCommand(List<Double> params){
        super(params);
    }
    @Override
    public double run(Turtle t) {
        t.setVisible(parameters.get(0) != 0);
        return parameters.get(0);
    }

}
