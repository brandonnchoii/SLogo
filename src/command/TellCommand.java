package command;


import java.util.List;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;
import turtle.Turtle;

public class TellCommand extends MultiTurtleCommand{

    public TellCommand(List<String> params, ObservableMap<String, Double> variableMap, ObservableMap<String, String> func, List<ObjectProperty> bind, ObservableList<Color> colors){
        super(params, variableMap, func, bind, colors);
    }
    @Override
    public double run(Turtle t) {
        if(parameters.contains(t.getID()))
            t.setActive(true);
        else
            t.setActive(false);
        return parameters.get(parameters.size()-1);
    }

    @Override
    protected String makeString(Map<Integer, Turtle> turtles){
        updateActive(makeIDs(), turtles);
        return super.makeString(turtles);

    }
    private void updateActive (List<Integer> makeIDs, Map<Integer, Turtle> turtles) {
        for (Turtle t: turtles.values()){
            if(makeIDs.contains(t.getID()))
                t.setActive(true);
            else
                t.setActive(false);
        }

    }


}

