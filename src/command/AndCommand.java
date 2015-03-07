package command;


import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class AndCommand extends Command{


    public AndCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
    }

    @Override
    public double run(Turtle t) {

        if(parameters.get(0) != 0 && parameters.get(1) != 0)
            return 1;
        return 0;
    }


}
