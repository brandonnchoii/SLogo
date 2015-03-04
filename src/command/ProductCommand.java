package command;

import java.util.List;

import turtle.Turtle;

public class ProductCommand extends Command{

    public ProductCommand(List<Double> params){
        super(params);
    }

    @Override
    public double run(Turtle t) {

        if(parameters.size() < 2)
            throw new IllegalArgumentException("Not enough parameters");

        double ret = 1;
        for(int i = 0; i < parameters.size(); i ++)
            ret *= parameters.get(i);
        return ret;
    }
}
