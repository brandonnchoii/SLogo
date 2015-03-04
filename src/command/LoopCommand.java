package command;

import java.util.List;

import turtle.Turtle;

public abstract class LoopCommand extends Command {

    protected double start;
    protected double end;
    protected double incr;
    protected String variable;
    protected List<String> strParameters;

    public LoopCommand(List<String> params){
        super();
        strParameters = params;
        loop = true;
    }

    public LoopCommand() {
        super();
        loop = true;
    }

    @Override
    public abstract double run(Turtle t);
    
    protected abstract void updateMap();
}
