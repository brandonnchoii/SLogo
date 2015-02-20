package command;

import java.util.List;

import turtle.Turtle;

public class GoToCommand extends Command {
	
	public GoToCommand(List<Double> params){
		super(params);
	}
	@Override
	public double run(Turtle t) {
		double pastX = t.getTranslateX();
		double pastY = t.getTranslateY();
		t.moveTo(parameters.get(0), parameters.get(1));
		return distance(pastX, pastY);
	}
	
	private double distance(double x, double y){
		return Math.sqrt(Math.pow(x-parameters.get(0), 2) + Math.pow(y-parameters.get(1), 2));
	}

}
