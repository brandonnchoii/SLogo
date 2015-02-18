package command;

import java.util.List;
import turtle.Turtle;

public class PowCommand extends Command{
	
	public PowCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		return Math.pow(parameters.get(0), parameters.get(1));
	}
	
	
}
