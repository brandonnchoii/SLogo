package command;

import turtle.Turtle;

public class PenQueryCommand extends Command{
	
	public PenQueryCommand(){
		super();
	}

	@Override
	public double run(Turtle t) {
		if(t.isDrawing())
			return 1;
		return 0;
	}
}
