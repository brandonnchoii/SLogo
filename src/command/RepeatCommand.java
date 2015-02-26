package command;

import java.util.List;

import turtle.Turtle;

public class RepeatCommand extends LoopCommand {

	public RepeatCommand(List<String> params) {
		super(params);
		try{
			start = 1;
			end = Double.parseDouble(params.get(1));
			incr = 1;
			variable = ":repcount";
		}
		catch(NumberFormatException e){
			throw new IllegalArgumentException("Illegal parameters");
		}
	}

	public RepeatCommand(){
		super();
	}

	@Override
	public double run(Turtle t) {
		return 0;
	}

}
