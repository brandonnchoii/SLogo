package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;


public abstract class IECommand extends Command {

    private final static String TRUE = "1";
    private final static String FALSE = "0";
    
    public IECommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
        updateMap();

    }

    protected void updateMap() {
        if(parameters.get(1) != 0)
            commandValues.put("IfCommand", TRUE);
        else
            commandValues.put("IfCommand", FALSE);
    }
    
    @Override
    public double run(Turtle t) {
        if(parameters.get(0) != 0) 
            return 0;
        return 1;
    }


}
