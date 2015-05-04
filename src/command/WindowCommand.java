package command;

import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class WindowCommand extends Command {
    private static final int TO_RETURN = 2;

    public WindowCommand (List<String> params,
                          ObservableMap<String, Double> variableMap,
                          ObservableMap<String, String> func,
                          List<ObjectProperty> bind,
                          ObservableList<Color> color) {
        super(params, variableMap, func, bind, color);
    }

    @Override
    public double doCommand (Turtle t) {
        t.makeBoundary(TO_RETURN);
        return TO_RETURN;
    }

}
