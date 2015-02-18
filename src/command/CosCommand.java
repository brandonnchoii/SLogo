package command;

import java.util.List;

import turtle.Turtle;

public class CosCommand extends Command{
	
	public CosCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		return Math.cos(Math.toRadians(parameters.get(0)));
	}
}
