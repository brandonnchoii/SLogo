package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class MakeUserInstructionCommand extends LoopCommand {


    public MakeUserInstructionCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }


    @Override
    public double run(Turtle t) {
        if(functions.keySet().contains(strParameters.get(1)))
            return 1;
        return 0;
    }

    public Map<String, String> updateFunctions(){
        if(functions.keySet().contains(strParameters.get(1)))
            functions.put(strParameters.get(1), strParameters.get(2));
        return functions;
    }

}
