package command;

import java.util.List;

import turtle.Turtle;

public class XcorCommand extends Command{
	
	public XcorCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		return t.getTranslateX();
	}
}
