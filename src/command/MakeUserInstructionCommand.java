package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class MakeUserInstructionCommand extends LoopCommand {

    private boolean exists;
    public MakeUserInstructionCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
        exists = functions.keySet().contains(strParameters.get(1));
    }


    @Override
    public double run(Turtle t) {
        if(exists)
            return 0;
        return 1;
    }

    public Map<String, String> updateFunctions(){
        if(exists)
            functions.put(strParameters.get(1), strParameters.get(2));
        return functions;
    }

}
