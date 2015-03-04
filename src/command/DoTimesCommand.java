package command;

import java.util.List;

import turtle.Turtle;

public class DoTimesCommand extends LoopCommand {

    public DoTimesCommand(List<String> params) {
        super(params);
        updateMap();

    }

    public DoTimesCommand(){
        super();
    }

    @Override
    protected void updateMap(){
        String[] loopInfo = strParameters.get(1).split(" ");
        commandValues.put("loopVariable", loopInfo[0]);
        commandValues.put("loopEnd",loopInfo[1]);
    }

    @Override
    public double run(Turtle t) {	
        return 0;
    }

}
