package command;

import java.util.List;

import turtle.Turtle;

public class DoTimesCommand extends LoopCommand {
	
	public DoTimesCommand(List<Double> params) {
		super(params);
		start = 1;
		end = 1;
		incr = 1;
	}

	public DoTimesCommand(){
		super();
	}

	@Override
	public double run(Turtle t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
