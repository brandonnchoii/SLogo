package command;

import turtle.Turtle;

public class IsShowingCommand extends Command{
	
	public IsShowingCommand(){
		super();
	}

	@Override
	public double run(Turtle t) {
		if(t.isVisible())
			return 1;
		return 0;
	}
}
