package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class MakeVariableCommand extends LoopCommand {


    public MakeVariableCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
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
