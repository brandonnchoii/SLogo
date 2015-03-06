package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class HomeCommand extends Command {

    public HomeCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }
    @Override
    public double run(Turtle t) {
        double pastX = t.getTranslateX();
        double pastY = t.getTranslateY();
        t.moveTo(0,0);
        t.setHeading(0);
        return distance(pastX, pastY);
    }

    private double distance(double x, double y){
        return Math.sqrt(x*x + y*y);
    }

}
