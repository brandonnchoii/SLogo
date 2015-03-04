package command;

import turtle.Turtle;

public class IsPenDownCommand extends Command{
	
	public IsPenDownCommand(){
		super();
	}

	@Override
	public double run(Turtle t) {
		if(t.isDrawing())
			return 1;
		return 0;
	}
}
