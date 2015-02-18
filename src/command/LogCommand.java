package command;

import java.util.List;
import turtle.Turtle;

public class LogCommand extends Command{
	
	public LogCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		return Math.log(parameters.get(0));
	}
	
	
}
