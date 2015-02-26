package command;

import java.util.ArrayList;
import java.util.List;

import turtle.Turtle;

public abstract class Command {

	protected List<Double> parameters;
	protected boolean loop;

	public Command(){
		parameters = new ArrayList<Double>();
		checkParams();
		loop = false;
	}
	public Command(List<Double> params) {
		parameters = params;
		loop = false;
	}

	public void addParam(double d){
		parameters.add(d);
	}

	public abstract double run(Turtle t);

	public boolean isLoop(){
		return loop;
	}
	private void checkParams(){
		if (parameters.contains(null)){
			throw new IllegalArgumentException("The parameters are invalid"); 
		}
	}


}
