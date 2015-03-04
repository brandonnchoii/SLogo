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

	public World() throws IOException {
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;
		myTurtles = new HashMap<>();
		myTurtles.put(0, (new Turtle(TURTLE_DEFAULT, 0)));
		myParser = new Parser("English");
	}

	public World(int h, int w) throws IOException {
		height = h;
		width = w;
		myTurtles = new HashMap<>();
		myTurtles.put(0, (new Turtle(TURTLE_DEFAULT, 0)));
		myParser = new Parser("English");
	}

	public World(int h, int w, Turtle t, String language) throws IOException {
		height = h;
		width = w;
		myTurtles = new HashMap<>();
		myTurtles.put(t.getID(), t);
		myParser = new Parser(language);
	}

	public abstract void fixPosition();

	public String oldListen(String input) {
		String param = "";
		for (String s : input.split("/n")) {
			int numCmds = myParser.initializeCommands(s);
			for (int i = 0; i < numCmds; i++) {
				Command c = myParser.parse(param);
				System.out.println("num " + i);
				if (c.isLoop()) {
					// param = "loop";
					LoopCommand loopCommand = (LoopCommand) c;
					loopCommand.readValues();
					for (double j = loopCommand.getStart()
							+ loopCommand.getIncr(); j < loopCommand.getEnd(); j += loopCommand
							.getIncr()) {
						param = "loop";
						myParser.updateVariable(loopCommand.getVariable(), j);
						myTurtle.act(myParser.parse(param));
					}
					myParser.resetRepcount();
				} else {
					param = myTurtle.act(c);
					// param = myTurtle.act(myParser.parse(param));
				}
			}
			param = "";
		}
		return param;
	}

	public String listen(String input) {
		String param = "";
		for (String s : input.split("/n")) {
			int numCommands = myParser.initializeCommands(s);
			Command c = myParser.parse(param);
			Map<String, String> commandValues = c.getCommandValues(myTurtles);
			for (int i = 0; i < numCommands; i++) {
				for (String id : commandValues.get("turtlesToAct").split("/n")) {
					if (commandValues.get("ifStatement").equals(TRUE)) {
						for (double j = Double.parseDouble(commandValues
								.get("loopStart")); j < Double
								.parseDouble(commandValues.get("loopEnd")); j += Double
								.parseDouble(commandValues.get("loopIncrement"))) {
							myParser.updateVariable(
									commandValues.get("loopVariable"), j);
							int turtleID = Integer.parseInt(id);
							try {
								param = myTurtles.get(turtleID).act(c);
							} catch (Exception e) {
								myTurtles.put(turtleID, new Turtle(
										TURTLE_DEFAULT, turtleID));
								param = myTurtles.get(turtleID).act(c);
							}
							myParser.resetRepcount();
							c = myParser.parse(param);
						}
					} else {
						return commandValues.get("0");
					}
				}
			}
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