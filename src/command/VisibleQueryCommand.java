package command;

import turtle.Turtle;

public class VisibleQueryCommand extends Command{
	
	public VisibleQueryCommand(){
		super();
	}

	@Override
	public double run(Turtle t) {
		if(t.isVisible())
			return 1;
		return 0;
	}
}
