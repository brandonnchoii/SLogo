package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class DoTimesCommand extends LoopCommand {

    public DoTimesCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }

    public DoTimesCommand(){
        super();
    }

    @Override
    protected void updateMap(){
        super.updateMap();
        commandValues.put("loopVariable", loopInfo[0]);  
        addInfo("loopEnd", 1);
    }



    @Override
    public double run(Turtle t) {	
        return 0;
    }

}
