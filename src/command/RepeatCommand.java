package command;

import java.util.List;

import turtle.Turtle;

public class RepeatCommand extends LoopCommand {

    public RepeatCommand(List<String> params) {
        super(params);
        updateMap();

    }

    public RepeatCommand(){
        super();
    }

    @Override
    public double run(Turtle t) {
        return 0;
    }
    
    @Override
    protected void updateMap() {
       commandValues.put("loopEnd", strParameters.get(1));
    }

}
