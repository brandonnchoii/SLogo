package command;

import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class ForCommand extends LoopCommand {

    public ForCommand(List<String> params, ObservableMap<String, Double> variableMap, ObservableMap<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
    }

    protected void updateMap(){
        super.updateMap();
        commandValues.put("loopVariable", loopInfo[0]);
        addInfo("loopStart", 1);
        addInfo("loopEnd", 2);
        addInfo("loopIncrement", 3);

    }

    @Override
    public double doCommand(Turtle t) {

        return 0;
    }

}
