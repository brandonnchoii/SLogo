package command;

import java.util.List;

import turtle.Turtle;

public class VisibleQueryCommand extends Command{
	
	public VisibleQueryCommand(List<Double> params){
		super(params);
	}

	@Override
	public double run(Turtle t) {
		if(t.isVisible())
			return 1;
		return 0;
	}
}
