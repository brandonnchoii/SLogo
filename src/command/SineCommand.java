package command;

import java.util.List;

import turtle.Turtle;

public class SineCommand extends Command{
	
	public SineCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		return Math.sin(Math.toRadians(parameters.get(0)));
	}
}
