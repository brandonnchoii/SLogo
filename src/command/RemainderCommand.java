package command;

import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class RemainderCommand extends Command {

    public RemainderCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
    }
    @Override
    public double run(Turtle t) {
        if(parameters.get(1) == 0)
            throw new IllegalArgumentException("Divide by zero");
        else
            return parameters.get(0) % parameters.get(1);
    }

}
