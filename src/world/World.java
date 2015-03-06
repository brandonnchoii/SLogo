package world;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import command.LoopCommand;
import command.Command;
import javafx.scene.paint.Color;
import parser.Parser;
import turtle.Turtle;

public abstract class World {

	protected int height;
	protected int width;
	// Turtle extends IV but eventaully, we want to not give WC or UI the entire
	// Turtle
	protected Map<Integer, Turtle> myTurtles;
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

	public World() throws IOException {
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;
		myTurtles = new HashMap<>();
		myTurtles.put(0, (new Turtle(TURTLE_DEFAULT, 0)));
		myParser = new Parser("English");
		myTurtle = myTurtles.get(0);
	}

	public World(int h, int w) throws IOException {
		height = h;
		width = w;
		myTurtles = new HashMap<>();
		myTurtles.put(0, (new Turtle(TURTLE_DEFAULT, 0)));
		myParser = new Parser("English");
		myTurtle = myTurtles.get(0);
	}

	public World(int h, int w, Turtle t, String language) throws IOException {
		height = h;
		width = w;
		myTurtles = new HashMap<>();
		myTurtles.put(t.getID(), t);
		myParser = new Parser(language);
		myTurtle = myTurtles.get(0);
	}

	public abstract void fixPosition();

//	public String oldListen(String input) {
//		String param = "";
//		for (String s : input.split("/n")) {
//			int numCmds = myParser.initializeCommands(s);
//			for (int i = 0; i < numCmds; i++) {
//				Command c = myParser.parse(param);
//				System.out.println("num " + i);
//				if (c.isLoop()) {
//					// param = "loop";
//					LoopCommand loopCommand = (LoopCommand) c;
//					loopCommand.readValues();
//					for (double j = loopCommand.getStart()
//							+ loopCommand.getIncr(); j < loopCommand.getEnd(); j += loopCommand
//							.getIncr()) {
//						param = "loop";
//						myParser.updateVariable(loopCommand.getVariable(), j);
//						myTurtle.act(myParser.parse(param));
//					}
//					myParser.resetRepcount();
//				} else {
//					param = myTurtle.act(c);
//					// param = myTurtle.act(myParser.parse(param));
//				}
//			}
//			param = "";
//		}
//		return param;
//	}

	public String listen(String input) {
		String param = "";
		for (String s : input.split("/n")) {
			int numCommands = myParser.initializeCommands(s);
			System.out.println("numCommands = " + numCommands);
			for (int i = 0; i < numCommands; i++) {
				Command c = myParser.parse(param);
				Map<String, String> commandValues = c.getCommandValues(myTurtles);
				System.out.println("param = " + param);
				for (String id : commandValues.get(TURTLES_TO_ACT).split("/n")) {
					if (commandValues.get(IF_STATEMENT).equals(TRUE)) {
						double loopStart = Double.parseDouble(commandValues
								.get(LOOP_START));
						double loopEnd = Double.parseDouble(commandValues
								.get(LOOP_END));
						double loopIncrement = Double.parseDouble(commandValues
								.get(LOOP_INCREMENT));
						for (double j = loopStart; j <= loopEnd; j += loopIncrement) {
//							myParser.updateVariable(
//									commandValues.get(LOOP_VARIABLE), j);
							System.out.println("loop# = " + j);
							int turtleID = Integer.parseInt(id);
							if (loopStart + loopIncrement == loopEnd) {
								param = runCommand(c, turtleID);
							} else {
								param = runCommand(myParser.parse("loop"),
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

	private String runCommand(Command command, int turtleID) {
		String param;
		try {
			param = myTurtles.get(turtleID).act(command);
		} catch (Exception e) {
			myTurtles.put(turtleID, new Turtle(TURTLE_DEFAULT, turtleID));
			param = myTurtles.get(turtleID).act(command);
		}
		return param;
	}

	public void setLanguage(String language) {
		myParser.setLanguage(language);
	}

	public Turtle getTurtle() {
		return myTurtle;
	}

	// public static void main(String[] args) throws IOException {
	// BoundedWorld test = new BoundedWorld();
	// test.listen("fd 100 /n rt 90 /n fd 100");
	// }

}
