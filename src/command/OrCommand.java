package command;


import java.util.List;

import turtle.Turtle;

public class OrCommand extends Command{


    public OrCommand(List<Double> params){
        super(params);
    }

    @Override
    public double run(Turtle t) {
        if(parameters.get(0) != 0 || parameters.get(1) != 0)
            return 1;
        return 0;
    }


}
