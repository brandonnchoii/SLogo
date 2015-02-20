package command;

import java.util.List;

import turtle.Turtle;

public class PenCommand extends Command {
	
	public PenCommand(List<Double> params){
		super(params);
	}
	@Override
	public double run(Turtle t) {
		t.setDrawing(parameters.get(0) != 0);
		return parameters.get(0);
	}

}
