package command;

import java.util.List;

import turtle.Turtle;

public class HideTurtleCommand extends Command {

    public HideTurtleCommand(List<Double> params){
        super(params);
    }
    @Override
    public double run(Turtle t) {
        t.setVisible(false);
        return 0;
    }

}
