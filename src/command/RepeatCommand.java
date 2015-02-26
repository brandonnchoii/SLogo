package command;

import java.util.List;

import turtle.Turtle;

public class RepeatCommand extends LoopCommand {
	
	public RepeatCommand(List<Double> params) {
		super(params);
		start = 1;
		end = 1;
		incr = 1;
	}

	public RepeatCommand(){
		super();
	}

	@Override
	public double run(Turtle t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
