package world;

/**
 * This class is responsible for passing the input to the parser, which
 * generates a Command that the World gives to the turtle(s)
 * 
 * @author Megan, Thomas
 *
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import command.Command;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.beans.property.ObjectProperty;
import javafx.scene.paint.Color;
import parser.Parser;
import turtle.Turtle;

public abstract class World {

	protected int height;
	protected int width;
	// Turtle extends IV but eventaully, we want to not give WC or UI the entire
	// Turtle
	protected Map<Integer, Turtle> myTurtles;
	protected ObservableMap<String, Double> variables;
	protected ObservableMap<String, String> functions;
	protected Turtle activeTurtle;
	private Parser myParser;
	private Turtle myTurtle;

	private static final int DEFAULT_HEIGHT = 100;
	private static final int DEFAULT_WIDTH = 100;
	private static final Color TURTLE_DEFAULT = Color.BLACK;
	private static final String TRUE = "1";
	private static final String TURTLES_TO_ACT = "turtlesToAct";
	private static final String LOOP_VARIABLE = "loopVariable";
	private static final String LOOP_INCREMENT = "loopIncrement";
	private static final String LOOP_END = "loopEnd";
	private static final String LOOP_START = "loopStart";
	private static final String IF_STATEMENT = "ifStatement";
	private List<ObjectProperty> bindings;
	private ObservableList<Color> colors;

	public World(ObservableMap<String, Double> var,
			ObservableMap<String, String> savedCommands,
			List<ObjectProperty> Bindings, ObservableList<Color> color)
			throws IOException {
		variables = var;
		functions = savedCommands;
		bindings = Bindings;
		colors = color;
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;
		myTurtles = new HashMap<>();
		myTurtles.put(0, (new Turtle(bindings, TURTLE_DEFAULT, 0)));
		myParser = new Parser("English", variables, savedCommands, bindings, colors);
		myTurtle = myTurtles.get(0);
	}

	public World(int h, int w, ObservableMap<String, Double> var,
			ObservableMap<String, String> fun, List<ObjectProperty> Bindings,
			ObservableList<Color> color) throws IOException {
		variables = var;
		functions = fun;
		bindings = Bindings;
		colors = color;
		height = h;
		width = w;
		myTurtles = new HashMap<>();
		myTurtles.put(0, (new Turtle(bindings, TURTLE_DEFAULT, 0)));
		myParser = new Parser("English", variables, fun, bindings, colors);
		myTurtle = myTurtles.get(0);

	}

	public World(int h, int w, Turtle t, String language,
			ObservableMap<String, Double> var, ObservableMap<String, String> fun,
			List<ObjectProperty> Bindings, ObservableList<Color> color)
			throws IOException {
		variables = var;
		functions = fun;
		bindings = Bindings;
		colors = color;
		height = h;
		width = w;
		myTurtles = new HashMap<>();
		myTurtles.put(t.getID(), t);
		myParser = new Parser(language, variables, fun, bindings, colors);
		myTurtle = myTurtles.get(0);

	}

	public abstract void fixPosition();

	// public String oldListen(String input) {
	// String param = "";
	// for (String s : input.split("/n")) {
	// int numCmds = myParser.initializeCommands(s);
	// for (int i = 0; i < numCmds; i++) {
	// Command c = myParser.parse(param);
	// System.out.println("num " + i);
	// if (c.isLoop()) {
	// // param = "loop";
	// LoopCommand loopCommand = (LoopCommand) c;
	// loopCommand.readValues();
	// for (double j = loopCommand.getStart()
	// + loopCommand.getIncr(); j < loopCommand.getEnd(); j += loopCommand
	// .getIncr()) {
	// param = "loop";
	// myParser.updateVariable(loopCommand.getVariable(), j);
	// myTurtle.act(myParser.parse(param));
	// }
	// myParser.resetRepcount();
	// } else {
	// param = myTurtle.act(c);
	// // param = myTurtle.act(myParser.parse(param));
	// }
	// }
	// param = "";
	// }
	// return param;
	// }

	/**
	 * 
	 * 
	 * @param String input
	 * @return String param
	 */
	public String listen(String input) {
		String param = "";
		for (String s : input.split("/n")) {
			int numCommands = myParser.initializeCommands(s);
			System.out.println("numCommands = " + numCommands);
			for (int i = 0; i < numCommands; i++) {
				Command c = myParser.parse(param);
				Map<String, String> commandValues = c
						.getCommandValues(myTurtles);
				System.out.println("param = " + param);
				for (String id : commandValues.get(TURTLES_TO_ACT).split("/n")) {
					if (commandValues.get(IF_STATEMENT).equals(TRUE)) {
						System.out.println("if = " + commandValues.get(IF_STATEMENT));
						double loopStart = Double.parseDouble(commandValues
								.get(LOOP_START));
						double loopEnd = Double.parseDouble(commandValues
								.get(LOOP_END));
						double loopIncrement = Double.parseDouble(commandValues
								.get(LOOP_INCREMENT));
						for (double j = loopStart; j <= loopEnd; j += loopIncrement) {
							System.out.println("loop# = " + j);
							myParser.updateVariable(
									commandValues.get(LOOP_VARIABLE), j);
							int turtleID = Integer.parseInt(id);
							if (loopStart == loopEnd) {
								System.out.println("loopEnd" + loopEnd);
								param = runCommand(c, turtleID);
							} else {
								param = runCommand(myParser.parse("list"),
										turtleID);
							}
						}
						myParser.resetRepcount();
					} else {
						// return commandValues.get("0");
					}
				}
			}
		}
		return param;

	}

	/**
	 * Trys to run command on turtle if it exists, if not creates appropriate
	 * turtle and runs command
	 * 
	 * @param Command command
	 * @param int turtleID
	 * @return String param
	 */
	private String runCommand(Command command, int turtleID) {
		String param;
		try {
			param = myTurtles.get(turtleID).act(command);
		} catch (Exception e) {
			myTurtles.put(turtleID, new Turtle(bindings, TURTLE_DEFAULT,
					turtleID));
			param = myTurtles.get(turtleID).act(command);
		}
		return param;
	}

    /**
     * Ability to change language of input
     * @param language
     */	
	public void setLanguage(String language) {
		myParser.setLanguage(language);
	}

	/**
	 * Returns turtle
	 * 
	 * @return Turtle
	 */
	public Turtle getTurtle() {
		return myTurtle;
	}

	// public static void main(String[] args) throws IOException {
	// BoundedWorld test = new BoundedWorld();
	// test.listen("fd 100 /n rt 90 /n fd 100");
	// }

}