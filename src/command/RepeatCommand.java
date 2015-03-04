package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class RepeatCommand extends LoopCommand {

    public RepeatCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
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
