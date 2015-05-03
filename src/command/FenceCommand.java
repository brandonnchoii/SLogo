package command;

import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;

public class FenceCommand extends BoundaryCommand {
    private final static int MY_BOUND = 3;
    public FenceCommand (List<String> params,
                         ObservableMap<String, Double> variableMap,
                         ObservableMap<String, String> func,
                         List<ObjectProperty> bind,
                         ObservableList<Color> color) {
        super(params, variableMap, func, bind, color);
        setBound(MY_BOUND);
        // TODO Auto-generated constructor stub
    }

}
