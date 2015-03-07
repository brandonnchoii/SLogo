package command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;

public abstract class LoopCommand extends Command {

    protected double start;
    protected double end;
    protected double incr;
    protected String variable;
    protected String[] loopInfo;

    public LoopCommand(List<String> params, ObservableMap<String, Double> variableMap, ObservableMap<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
        updateMap();
        loop = true;
    }
  
    protected List<Double> makeParameters(){
        return new ArrayList<Double>();
    }
    
    protected void updateMap(){
        loopInfo = strParameters.get(1).split(" ");
        System.out.println("INFO" + loopInfo);
    }
    
    protected void addInfo(String s, int i){
        if(loopInfo[i].matches(syntax.getString("Variable")))
            commandValues.put("loopEnd", readVariable(loopInfo[i]) + "");
        else
            commandValues.put("loopEnd", loopInfo[i]);
    }
}
