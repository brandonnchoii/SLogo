package command;

import java.util.List;
import turtle.Turtle;

public class LogCommand extends Command{

	public LogCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		if(parameters.get(0) == 0)
			throw new IllegalArgumentException("Log zero");
		else
			return Math.log(parameters.get(0));
	}


}
