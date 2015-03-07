package command;

import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class RepeatCommand extends LoopCommand {

    public RepeatCommand(List<String> params, ObservableMap<String, Double> variableMap, ObservableMap<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);

    }


    @Override
    public double run(Turtle t) {
        return 0;
    }

    @Override
    protected void updateMap() {
        super.updateMap();
        addInfo("loopEnd", 0);
    }

}
