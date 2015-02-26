package command;


import java.util.List;
import turtle.Turtle;

public class RepeatCommand extends Command {

	public RepeatCommand(List<Double> params){
		super(params);
	}

	public RepeatCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double run(Turtle t) {
		if(parameters.get(1) < parameters.get(0)) 
			return 0;
		return 1;
	}
}

