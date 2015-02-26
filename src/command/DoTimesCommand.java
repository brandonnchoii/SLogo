package command;

import java.util.List;

import turtle.Turtle;

public class DoTimesCommand extends LoopCommand {
	
	public DoTimesCommand(List<String> params) {
		super(params);
		try{
			start = 1;
			incr = 1;
			String[] parts = params.get(1).split(" ");
			variable = parts[0];
			end = Double.parseDouble(parts[1]);
			

		}
		catch(NumberFormatException e){
			throw new IllegalArgumentException("Illegal Params");
		}
		
	}

	public DoTimesCommand(){
		super();
	}

	@Override
	public double run(Turtle t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
