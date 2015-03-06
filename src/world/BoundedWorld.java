package world;

import java.io.IOException;
import javafx.collections.ObservableMap;
import turtle.Turtle;

public class BoundedWorld extends World{

    public BoundedWorld(ObservableMap<String, Double> variables, ObservableMap<String, String> functions) throws IOException {
        super(variables, functions);
    }

    public BoundedWorld(int h, int w, ObservableMap<String, Double> variables, ObservableMap<String, String> functions) throws IOException{
        super(h,w, variables, functions);
    }

    public BoundedWorld(int h, int w, Turtle t, String l, ObservableMap<String, Double> variables, ObservableMap<String, String> functions) throws IOException{
        super(h,w,t,l, variables, functions);
    }


    @Override
    public void fixPosition () {

    }

}
