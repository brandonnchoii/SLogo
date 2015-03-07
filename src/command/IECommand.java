package command;

import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;
import turtle.Turtle;


public abstract class IECommand extends Command {

    private final static String TRUE = "1";
    private final static String FALSE = "0";
    
    public IECommand(List<String> params, ObservableMap<String, Double> variableMap, ObservableMap<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
        updateMap();

    }

    protected void updateMap() {
        if(parameters.get(1) != 0)
            commandValues.put("IfCommand", TRUE);
        else
            commandValues.put("IfCommand", FALSE);
    }
    
    @Override
    public double doCommand(Turtle t) {
        if(parameters.get(0) != 0) 
            return 0;
        return 1;
    }


}
