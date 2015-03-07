package command;

import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

public class IfElseCommand extends IECommand {

    public IfElseCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
    }


    

}
