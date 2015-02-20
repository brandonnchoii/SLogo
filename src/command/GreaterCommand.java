package command;


import java.util.List;

import turtle.Turtle;

public class GreaterCommand extends Command{
	
	public GreaterCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		if(parameters.get(0) > parameters.get(1))
			return 1;
		return 0;
	}
	
	
}
