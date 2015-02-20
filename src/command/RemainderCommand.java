package command;

import java.util.List;

import turtle.Turtle;

public class RemainderCommand extends Command {
	
	public RemainderCommand(List<Double> params){
		super(params);
	}
	@Override
	public double run(Turtle t) {
		return parameters.get(0) % parameters.get(1);
	}

}
