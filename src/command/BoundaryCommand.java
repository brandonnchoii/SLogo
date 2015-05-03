package command;

import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;
import turtle.Turtle;

public abstract class BoundaryCommand extends Command {
    private int myBound;
    public BoundaryCommand (List<String> params,
                            ObservableMap<String, Double> variableMap,
                            ObservableMap<String, String> func,
                            List<ObjectProperty> bind,
                            ObservableList<Color> color) {
        super(params, variableMap, func, bind, color);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double doCommand (Turtle t) {
        t.setBound(myBound);
        return myBound;
    }
    
    public void setBound(int newB){
        myBound = newB;
    }

}
