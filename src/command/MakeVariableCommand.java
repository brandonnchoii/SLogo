package command;

import turtle.Turtle;

public class MakeCommand extends Command {
	private double expr;
	
	public MakeCommand(double d){
		super(null);
		expr = d;
	}

	@Override
	public double run(Turtle t) {
		return expr;
	}

}
