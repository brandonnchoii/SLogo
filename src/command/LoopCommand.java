package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class LoopCommand extends Command {

    protected double start;
    protected double end;
    protected double incr;
    protected String variable;
    protected List<String> strParameters;
    protected String[] loopInfo;

    public LoopCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
        updateMap();
        loop = true;
    }

    public LoopCommand() {
        super();
        loop = true;
    }
    
    protected List<Double> createParameters(){
        return new ArrayList<Double>();
    }
    
    protected void updateMap(){
        loopInfo = strParameters.get(1).split(" ");
    }
    
    protected void addInfo(String s, int i){
        if(loopInfo[i].matches(syntax.getString("Variable")))
            commandValues.put("loopEnd", readVariable(loopInfo[i]) + "");
        else
            commandValues.put("loopEnd", loopInfo[i]);
    }
}
