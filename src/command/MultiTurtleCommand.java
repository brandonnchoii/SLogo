package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class MultiTurtleCommand extends Command {

    public MultiTurtleCommand(List<String> params, Map<String, Double> variableMap){
        super(params, variableMap);
    }
    
    protected List<Integer> makeIDs(){
        List<Integer> ret = new ArrayList<>();
        String[] s = strParameters.get(1).split("\\p{Space}");
        for(String id: s)
            ret.add(Integer.parseInt(id));
        return ret;
    }

}