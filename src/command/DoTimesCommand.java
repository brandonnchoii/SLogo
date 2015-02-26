package command;

import java.util.List;

import turtle.Turtle;

public class DoTimesCommand extends Command {

	public DoTimesCommand(List<Double> params){
		super(params);
	}

	public DoTimesCommand() {
		// TODO Auto-generated constructor stub
	}
		
	@Override
	public double run(Turtle t) {
		if(parameters.get(1) < parameters.get(0)) 
			return 0;
		return 1;
	}

}
