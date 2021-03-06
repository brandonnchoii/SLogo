package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;

public abstract class ArithmeticCommand extends Command{

    public ArithmeticCommand(List<String> params, ObservableMap<String, Double> variableMap, ObservableMap<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
    }

    protected void checkLegal(){
        if(parameters.size() < 2)
            throw new IllegalArgumentException("Not enough parameters");

    }

    public double doCommand(Turtle t){
        double ret = parameters.get(0);
        for(int i = 1; i < parameters.size(); i ++)
            ret = doMath(ret, parameters.get(i));
        return ret;
    }

    protected abstract double doMath(double a, double b);
}
