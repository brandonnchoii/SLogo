package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class RepeatCommand extends LoopCommand {

    public RepeatCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
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
       super.updateMap();
       addInfo("loopEnd", 0);
    }

}
