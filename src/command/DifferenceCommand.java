package command;

import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class DifferenceCommand extends ArithmeticCommand{

    public DifferenceCommand(List<String> params, ObservableMap<String, Double> variableMap, ObservableMap<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);   
    }


    @Override
    protected double doMath (double a, double b) {
        return  a  - b;
    }


    @Override
    public double doCommand (Turtle t) {
        return super.doCommand(t);
    }
}
