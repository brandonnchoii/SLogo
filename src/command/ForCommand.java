package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class ForCommand extends LoopCommand {

    public ForCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }

    public ForCommand(){
        super();
    }

    protected void updateMap(){
        String[] loopInfo = strParameters.get(1).split(" ");
        commandValues.put("loopVariable", loopInfo[0]);
        commandValues.put("loopStart", loopInfo[1]);
        commandValues.put("loopEnd",loopInfo[2]);
        commandValues.put("loopIncrement",loopInfo[3]);
    }
    
    @Override
    public double run(Turtle t) {

        return 0;
    }

}
