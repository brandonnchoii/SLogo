package command;

import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class HomeCommand extends Command {

    public HomeCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
    }
    @Override
    public double run(Turtle t) {
        double pastX = t.getTranslateX();
        double pastY = t.getTranslateY();
        t.moveTo(0,0);
        t.setHeading(0);
        return distance(pastX, pastY);
    }

    private double distance(double x, double y){
        return Math.sqrt(x*x + y*y);
    }

}
