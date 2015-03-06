package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class MakeVariableCommand extends LoopCommand {


    public MakeVariableCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }

    public List<Double> createParameters(){
        List<Double> params = new ArrayList<Double>();
        params.add(addParam(strParameters.get(2)));
        return params;
    }
    @Override
    public double run(Turtle t) {
        return parameters.get(0);
    }

    public Map<String, Double> updateVariables(){
        variables.put(strParameters.get(1), parameters.get(0));
        return variables;
    }

}
