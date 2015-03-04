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

    public LoopCommand(List<String> params, Map<String, Double> variables){
        super(params, variables);
        updateMap();
        loop = true;
    }

    public LoopCommand() {
        super();
        loop = true;
    }
    
    protected ArrayList<Double> createParameters(){
        return new ArrayList<Double>();
    }
    
    protected abstract void updateMap();
}
