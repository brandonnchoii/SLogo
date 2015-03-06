package command;

import java.util.List;
import java.util.Map;
import turtle.Turtle;

public class ProductCommand extends ArithmeticCommand{

    public ProductCommand(List<String> params, Map<String, Double> variableMap, Map<String, String> func){
        super(params, variableMap, func);
    }

    @Override
    public double run (Turtle t) {
        return super.run();
    }

    @Override
    protected double doMath (double a, double b) {
        return a * b;
    }
}
