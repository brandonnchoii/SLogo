package command;

import java.util.List;

import turtle.Turtle;

public class HomeCommand extends Command {
	
	public HomeCommand(List<Double> params){
		super(params);
	}
	@Override
	public double run(Turtle t) {
		double pastX = t.getTranslateX();
		double pastY = t.getTranslateY();
		t.moveTo(0,0);
		return distance(pastX, pastY);
	}
	
	private double distance(double x, double y){
		return Math.sqrt(x*x + y*y);
	}

}
