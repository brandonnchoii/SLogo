package command;

import java.util.List;

import turtle.Turtle;

public class YcorCommand extends Command{
	
	public YcorCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		return t.getTranslateY();
	}
}
