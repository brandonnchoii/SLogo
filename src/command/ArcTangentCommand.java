package command;


import java.util.List;

import turtle.Turtle;

public class AtanCommand extends Command{
	
	public AtanCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		return Math.atan(Math.toDegrees(parameters.get(0)));
	}
	
	
}
