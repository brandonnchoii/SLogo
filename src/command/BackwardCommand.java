package command;

import java.util.List;

import turtle.Turtle;

public class BackwardCommand extends Command {
	
	public BackwardCommand(List<Double> params){
		super(params);
	}
	@Override
	public double run(Turtle t) {
		t.move(-1*parameters.get(0));
		return parameters.get(0);
	}

}
