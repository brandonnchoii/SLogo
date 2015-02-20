package command;

import turtle.Turtle;

public class XcorCommand extends Command{
	
	public XcorCommand(){
		super();
	}

	@Override
	public double run(Turtle t) {
		return t.getTranslateX();
	}
}
