package command;

import java.util.List;

import turtle.Turtle;

public class SumCommand extends Command{
	
	public SumCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		return parameters.get(0) + parameters.get(1);
	}
}
