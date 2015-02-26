package command;

import java.util.List;

import turtle.Turtle;

public class DoTimesCommand extends LoopCommand {

	public DoTimesCommand(List<String> params) {
		super(params);

	}

	public DoTimesCommand(){
		super();
	}

	public void readValues(){
		try{
			start = 1;
			incr = 1;
			String[] loopInfo = strParameters.get(1).split(" ");
			variable = loopInfo[0];
			end = Double.parseDouble(loopInfo[1]);


		}
		catch(NumberFormatException e){
			throw new IllegalArgumentException("Illegal Params");
		}
	}
	@Override
	public double run(Turtle t) {	

		// TODO Auto-generated method stub
		return 0;
	}

}
