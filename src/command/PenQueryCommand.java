package command;

import java.util.List;

import turtle.Turtle;

public class PenQueryCommand extends Command{
	
	public PenQueryCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		if(t.isDrawing())
			return 1;
		return 0;
	}
}
