package world;

import java.io.IOException;
import javafx.collections.ObservableMap;
import turtle.Turtle;

public class UnboundedWorld extends World{

    public UnboundedWorld(ObservableMap<String, Double> variables, ObservableMap<String, String> functions) throws IOException{
        super(variables, functions);
    }

    public UnboundedWorld(int h, int w, ObservableMap<String, Double> variables, ObservableMap<String, String> functions) throws IOException{
        super(h,w, variables, functions);
    }

    public UnboundedWorld(int h, int w, Turtle t, String l, ObservableMap<String, Double> variables, ObservableMap<String, String> functions) throws IOException{
        super(h,w,t,l, variables, functions);
    }


    @Override
    public void fixPosition () {
        for(Turtle t: myTurtles.values())
            t.fixPos(height, width);
    }

}
