package command;

import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class MakeUserInstructionCommand extends LoopCommand {

    private boolean exists;
    public MakeUserInstructionCommand(List<String> params, ObservableMap<String, Double> variableMap, ObservableMap<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
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
