package command;

import java.util.List;

import turtle.Turtle;

public class ForCommand extends Command {

	public ForCommand(List<Double> params){
		super(params);
	}

	public ForCommand() {
		// TODO Auto-generated constructor stub
	}
		
	@Override
	public double run(Turtle t) {
		if(parameters.get(1) < parameters.get(0)) 
			return 0;
		return 1;
	}

}
