package command;

import java.util.List;

import turtle.Turtle;

public class HeadingCommand extends Command{
	
	public HeadingCommand(){
		super();
	}

	@Override
	public double run(Turtle t) {
		return t.getDirection();
	}
}
