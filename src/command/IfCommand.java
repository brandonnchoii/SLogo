package command;

import java.util.List;

import turtle.Turtle;

public class IfCommand extends Command {

    public IfCommand(List<Double> params){
        super(params);
    }

    public IfCommand() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public double run(Turtle t) {
        if(parameters.get(0) != 0) 
            return 0;
        return 1;
    }

}
