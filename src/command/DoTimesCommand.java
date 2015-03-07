package command;

import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class DoTimesCommand extends LoopCommand {

    public DoTimesCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
    }

    @Override
    protected void updateMap(){
        super.updateMap();
        commandValues.put("loopVariable", loopInfo[0]);  
        addInfo("loopEnd", 1);
    }



    @Override
    public double run(Turtle t) {	
        return 0;
    }

}
