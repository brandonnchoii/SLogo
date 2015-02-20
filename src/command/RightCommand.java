package command;

import java.util.List;

import turtle.Turtle;

public class RightCommand extends Command {
	
	public RightCommand(List<Double> params){
		super(params);
	}
	@Override
	public double run(Turtle t) {
		t.rotate(parameters.get(0));
		return parameters.get(0);
	}

}
