package command;

import java.util.List;

import turtle.Turtle;

public class QuotientCommand extends Command{
	
	public QuotientCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		return parameters.get(0) / parameters.get(1);
	}
}
