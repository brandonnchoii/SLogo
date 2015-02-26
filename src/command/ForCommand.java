package command;

import java.util.List;

import turtle.Turtle;

public class ForCommand extends LoopCommand {
	
	public ForCommand(List<Double> params) {
		super(params);
		start = 1;
		end = params.get();
		incr = params.get();
	}

	public ForCommand(){
		super();
	}

	@Override
	public double run(Turtle t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
