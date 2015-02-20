package command;

import java.util.List;

import turtle.Turtle;

public class SinCommand extends Command{
	
	public SinCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		return Math.sin(Math.toRadians(parameters.get(0)));
	}
}
