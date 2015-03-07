package command;

import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class SetTowardsCommand extends Command {

    public SetTowardsCommand(List<String> params, ObservableMap<String, Double> variableMap, ObservableMap<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
    }
    @Override
    public double run(Turtle t) {
        double pastDir = t.getDirection();
        double newDir = Math.toDegrees(Math.atan2(parameters.get(1), parameters.get(0)));
        t.setHeading(newDir);
        return pastDir - newDir;
    }


}
