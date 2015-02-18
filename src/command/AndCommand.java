package command;


import java.util.List;

import turtle.Turtle;

public class AndCommand extends Command{


	public AndCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		if(parameters.get(0) != 0 && parameters.get(1) != 0)
			return 1;
		return 0;
	}


}
