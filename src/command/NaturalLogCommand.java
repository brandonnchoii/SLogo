package command;

import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class NaturalLogCommand extends Command{

    public NaturalLogCommand(List<String> params, ObservableMap<String, Double> variableMap, ObservableMap<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
    }

    @Override
    public double doCommand(Turtle t) {
        if(parameters.get(0) == 0)
            throw new IllegalArgumentException("Log zero");
        else
            return Math.log(parameters.get(0));
    }


}
