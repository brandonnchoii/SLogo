package command;

import java.util.List;

import turtle.Turtle;

public abstract class LoopCommand extends Command {


	protected double start;
	protected double end;
	protected double incr;
	protected String variable;
	protected List<String> strParameters;

	public LoopCommand(List<String> params){
		super();
		strParameters = params;
		loop = true;
	}

	public LoopCommand() {
		super();
		loop = true;
	}

	@Override
	public double run(Turtle t) {
		if(parameters.get(parameters.size()-1) < parameters.get(0)) 
			return 0;
		return 1;
	}

	public double getStart(){
		return start;
	}

	public double getEnd(){
		return end;
	}

	public double getIncr(){
		return incr;
	}
	
	public String getVariable(){
		return variable;
	}

}
