package command;

import java.util.List;

import turtle.Turtle;

public class ForCommand extends LoopCommand {

	public ForCommand(List<String> params) {
		super(params);
	}

	public ForCommand(){
		super();
	}
	
	public void readValues(){
		try{
			String[] loopInfo = strParameters.get(1).split(" ");
			variable = loopInfo[0];
			start = Double.parseDouble(loopInfo[1]);
			end = Double.parseDouble(loopInfo[2]);
			incr = Double.parseDouble(loopInfo[3]);
		}
		catch(NumberFormatException e){
			throw new IllegalArgumentException("Invalid Parameters");
		}
	}
	@Override
	public double run(Turtle t) {
		
		return 0;
	}

}
