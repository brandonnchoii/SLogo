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
    private final static String IFLOOP = "2";
    
    public IECommand(List<String> params, ObservableMap<String, Double> variableMap, ObservableMap<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
        updateMap();

    }

    protected void updateMap() {
    	commandValues.put("loopIncrement", IFLOOP);
        commandValues.put("loopEnd", IFLOOP);
        if(parameters.get(0) != 0.)
            commandValues.put("ifStatement", TRUE);
        else
            commandValues.put("ifStatement", FALSE);
    }
    
    @Override
    public double doCommand(Turtle t) {
        if(parameters.get(0) != 0.) 
            return 1;
        return 0;
    }


}
