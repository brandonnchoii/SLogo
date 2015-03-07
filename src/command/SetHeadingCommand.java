package command;

import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class SetHeadingCommand extends Command {

    public SetHeadingCommand(List<String> params,ObservableMap<String, Double> variableMap, ObservableMap<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
    }
    
    @Override
    public double doCommand(Turtle t) {
        double pastDir = t.getDirection();
        t.setHeading(parameters.get(0));
        return Math.abs(parameters.get(0)-pastDir);
    }

}
