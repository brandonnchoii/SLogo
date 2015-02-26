package factories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import command.*;


public class CommandFactory {

	private Map<String, Double> variables;
	private List<Double> falseParams;
	private List<Double> trueParams;
	private List<Double> homeParams;
	private String language;

	private ResourceBundle translationMap;
	private ResourceBundle paramMap;
	private ResourceBundle syntax;

	private static final double TRUE = 1;
	private static final double FALSE = 0;
	private static final double DIMENSIONS = 2;

	public CommandFactory(String l)  {
		variables = new HashMap<>();
		language = l;

		
		setTranslationMap();

		paramMap = ResourceBundle.getBundle("resources.parameters/numParameters");
		syntax = ResourceBundle.getBundle("resources.languages/Syntax");

		makeDefaults();
		
		
	}

	private void makeDefaults(){
		falseParams = new ArrayList<>();
		falseParams.add(FALSE);

		trueParams = new ArrayList<>();
		trueParams.add(TRUE);

		homeParams = new ArrayList<>();
		while(homeParams.size() <= DIMENSIONS)
			homeParams.add(0.);
	}

	private void setTranslationMap(){
		translationMap = ResourceBundle.getBundle("resources.languages/" + language);
	}
	
	public Command createCommand(List<String> parts){
		List<Double> params = new ArrayList<Double>();
		String command = parts.get(0);
		if(translateCommand(command).equals("MakeVariable")){
			return addVariable(parts.get(1),parts.get(2));
		}
		String cmd = translateCommand(command);
		params = makeParams(parts);
		if (params.contains(null)){
			throw new IllegalArgumentException("The parameters are invalid"); 
		}

		Map <String, Command> cmdMap = makeCommandMap(params);
		Command ret = cmdMap.get(cmd);
		if(ret == null)
			throw new IllegalArgumentException("The command is invalid");

		return ret;
	}

	private String translateCommand(String s){
		for(String key: translationMap.keySet()){
			String val = translationMap.getString(key);
			for (String sub: val.split(","))
				if(sub.equals(s))
					return key;
			for(String sub: val.split("\\|"))
				if(sub.equals(s))
					return key;
		}

		throw new IllegalArgumentException("Illegal Command");
	}

	public int getNumParameters(String s){
		return Integer.parseInt(paramMap.getString(translateCommand(s)));
	}

	public void setLanguage(String l) {
		language = l;
		setTranslationMap();
	}

	private Map<String, Command> makeCommandMap(List<Double> params){
		Map<String, Command> paramMap = new HashMap<String, Command>();
		paramMap.putAll(turtleCommands(params));
		paramMap.putAll(queryCommands(params));
		paramMap.putAll(mathCommands(params));
		paramMap.putAll(booleanCommands(params));
		paramMap.putAll(loopCommands(params));
		return paramMap;
	}

	private Map<String, Command> booleanCommands(List<Double> params){
		Map<String, Command> commands = new HashMap<>();
		commands.put("LessThan", new LessCommand(params));
		commands.put("GreaterThan", new GreaterCommand(params));
		commands.put("Equal", new EqualsCommand(params));
		commands.put("NotEqual", new NotEqualsCommand(params));
		commands.put("And", new AndCommand(params));
		commands.put("Or", new OrCommand(params));
		commands.put("Not", new NotCommand(params));
		return commands;
	}

	private Map<String, Command> mathCommands(List<Double> params){
		Map<String, Command> commands = new HashMap<>();
		commands .put("Sum", new SumCommand(params));
		commands.put("Difference", new DifferenceCommand(params));
		commands.put("Product", new ProductCommand(params));
		commands.put("Quotient", new QuotientCommand(params));
		commands.put("Remainder", new RemainderCommand(params));
		commands.put("Minus", new MinusCommand(params));
		commands.put("Random", new RandomCommand(params));
		commands.put("Sine", new SinCommand(params));
		commands.put("Cosine", new CosCommand(params));
		commands.put("Tangent", new TanCommand(params));
		commands.put("ArcTangent", new AtanCommand(params));
		commands.put("NaturalLog", new LogCommand(params));
		commands.put("Power", new PowCommand(params));
		return commands;
	}

	private Map<String, Command> queryCommands(List<Double> params) {
		Map<String, Command> commands = new HashMap<>();
		// TODO Auto-generated method stub
		commands .put("XCoordinate", new XcorCommand());
		commands.put("YCoordinate", new YcorCommand());
		commands.put("Heading", new HeadingCommand());
		commands.put("IsPenDown", new PenQueryCommand());
		commands.put("IsShowing", new VisibleQueryCommand());

		return commands;
	}

	private Map<String, Command> turtleCommands(List<Double> params) {
		Map<String, Command> commands = new HashMap<>();
		commands.put("Forward", new ForwardCommand(params));
		commands.put("Backward", new BackwardCommand(params));
		commands.put("Left", new LeftCommand(params));
		commands.put("Right", new RightCommand(params));
		commands.put("SetHeading", new SetHeadingCommand(params));
		commands.put("SetTowards", new TowardsCommand(params));
		commands.put("SetPosition", new GoToCommand(params));
		commands.put("PenDown", new PenCommand(trueParams));
		commands.put("PenUp", new PenCommand(falseParams));
		commands.put("ShowTurtle", new VisibleCommand(trueParams));
		commands.put("HideTurtle", new VisibleCommand(falseParams));
		commands.put("Home", new GoToCommand(homeParams));
		commands.put("ClearScreen", null);

		return commands;
	}

	private Map<String, Command> loopCommands(List<Double> params){
		Map<String, Command> commands = new HashMap<>();
		params.add(this.readVariable(":repcount"));
		commands.put("Repeat", new LoopCommand(params));
		commands.put("DoTimes", new LoopCommand(params));
		commands.put("For", new LoopCommand(params));
		commands.put("If", new IfCommand(params));
		commands.put("IfElse", new IfCommand(params));
		//commands.put("MakeUserInstruction", new MakeUserInstructionCommand(params));
		return commands;
	}

	private Command addVariable(String s, String d){
		try{
			variables.put(s,Double.parseDouble(d));
			return new MakeCommand(Double.parseDouble(d));
		}
		catch(NumberFormatException e){
			throw e;
		}
	}

	private List<Double> makeParams(List<String> parts){
		List<Double> params = new ArrayList<Double>();
		for (int i = 1; i < parts.size(); i ++){
			params.add(addParam(parts.get(i)));
		}

		return params;
	}

	private Double addParam(String s){
		if(isVariable(s))
			return readVariable(s);
		else
			try{
				return Double.parseDouble(s);
			}
		catch(NumberFormatException e){
			return null;
		}
	}

	private boolean isVariable(String s){ 
		return s.matches(syntax.getString("Variable"));
	}

	private double readVariable(String s){
		return variables.get(s);
	}
	
	public void increment(String s, String i){
		variables.put(s, variables.get(s) + Double.parseDouble(i));
	}
	
	public void clearRepCount(){
		variables.remove(":repCount");
	}

}