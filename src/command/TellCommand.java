package command;

import java.util.List;

import turtle.Turtle;

public class TellCommand extends Command{
	
	public TellCommand(){
		
	}
	
	public TellCommand(List<Double> params){
		super(params);
	}
	@Override
	public double run(Turtle t) {
		if(parameters.contains(t.getID()))
			t.setActive(true);
		else
			t.setActive(false);
		return parameters.get(parameters.size()-1);
	}

}
