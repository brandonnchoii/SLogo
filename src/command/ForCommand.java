package command;

import java.util.List;

import turtle.Turtle;

public class ForCommand extends LoopCommand {

	public ForCommand(List<String> params) {
		super(params);
		try{
			String[] parts = params.get(1).split(" ");
			variable = parts[0];
			start = Double.parseDouble(parts[1]);
			end = Double.parseDouble(parts[2]);
			incr = Double.parseDouble(parts[3]);
		}
		catch(NumberFormatException e){
			throw new IllegalArgumentException("Invalid Parameters");
		}

	}

	public ForCommand(){
		super();
	}

	@Override
	public double run(Turtle t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
