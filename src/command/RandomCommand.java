package command;

import java.util.List;

import turtle.Turtle;

public class RandomCommand extends Command{
	
	public RandomCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		return parameters.get(0) * Math.random();
	}
}
