package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class DifferenceCommand extends ArithmeticCommand{

    public DifferenceCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);    
    }


    @Override
    protected double doMath (double a, double b) {
        return  a  - b;
    }


    @Override
    public double run (Turtle t) {
        return super.run();
    }
}
