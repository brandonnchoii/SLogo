package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class ForCommand extends LoopCommand {

    public ForCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }

    public ForCommand(){
        super();
    }

    protected void updateMap(){
        super.updateMap();
        commandValues.put("loopVariable", loopInfo[0]);
        addInfo("loopStart", 1);
        addInfo("loopEnd", 2);
        addInfo("loopIncrement", 3);

    }

    @Override
    public double run(Turtle t) {

        return 0;
    }

}
