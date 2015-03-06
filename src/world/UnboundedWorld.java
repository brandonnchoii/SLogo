package world;

import java.io.IOException;
import java.util.List;
import javafx.collections.ObservableMap;
import turtle.Turtle;
import javafx.beans.property.ObjectProperty;

public class UnboundedWorld extends World{


    public UnboundedWorld(ObservableMap<String, Double> variables, ObservableMap<String, String> functions, List<ObjectProperty> bindings) throws IOException {
        super(variables, functions, bindings);
    }

    public UnboundedWorld(ObservableMap<String, Double> variables, ObservableMap<String, String> functions, List<ObjectProperty> bindings, int h, int w) throws IOException{
        super( h,w, variables, functions, bindings);
    }


    public UnboundedWorld(int h, int w, Turtle t, String l, ObservableMap<String, Double> variables, ObservableMap<String, String> functions, List<ObjectProperty> bindings) throws IOException{
        super(h,w,t,l, variables, functions, bindings);
    }

    @Override
    public void fixPosition () {
        for(Turtle t: myTurtles.values())
            t.fixPos(height, width);
    }

}
