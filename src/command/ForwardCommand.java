package command;

import java.util.List;

import turtle.Turtle;

public class ForwardCommand extends Command {
	
	public ForwardCommand(List<Double> params){
		super(params);
	}
	@Override
	public double run(Turtle t) {
		t.move(parameters.get(0));
		return parameters.get(0);
	}

}
