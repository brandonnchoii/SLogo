package command;

import java.util.List;

import turtle.Turtle;

public class SumCommand extends Command{
	
	public SumCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {

		if(parameters.size() < 2)
			throw new IllegalArgumentException("Not enough parameters");
		
		double ret = 0;
		for(double d: parameters)
			ret += d;
		return ret;
	}
}
