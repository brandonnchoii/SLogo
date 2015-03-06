package command;

import java.util.List;

import turtle.Turtle;

public class SetTowardsCommand extends Command {
	
	public SetTowardsCommand(List<Double> params){
		super(params);
	}
	@Override
	public double run(Turtle t) {
		double pastDir = t.getDirection();
		double newDir = Math.toDegrees(Math.atan2(parameters.get(1), parameters.get(0)));
		t.setHeading(newDir);
		return pastDir - newDir;
	}
		
	
}
