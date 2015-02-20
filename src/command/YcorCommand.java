package command;

import turtle.Turtle;

public class YcorCommand extends Command{
	
	public YcorCommand(){
		super();
	}

	@Override
	public double run(Turtle t) {
		return t.getTranslateY();
	}
}
