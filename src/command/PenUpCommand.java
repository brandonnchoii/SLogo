package command;

import java.util.List;

import turtle.Turtle;

public class PenUpCommand extends Command {
	
	public PenUpCommand(List<Double> params){
		super(params);
	}
	@Override
	public double run(Turtle t) {
		t.setDrawing(false);
		return 0;
	}

}
