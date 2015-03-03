package command;

import turtle.Turtle;

public class MakeVariableCommand extends Command {
	private double expr;
	
	public MakeVariableCommand(double d){
		super(null);
		expr = d;
	}

	@Override
	public double run(Turtle t) {
		return expr;
	}

}
