package world;

import java.io.IOException;

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
	protected Turtle myTurtle;
	private Parser myParser;

	private static final int DEFAULT_HEIGHT = 100;
	private static final int DEFAULT_WIDTH = 100;
	private static final Color TURTLE_DEFAULT = Color.BLACK;

	public World() throws IOException {
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;

		myTurtle = new Turtle(TURTLE_DEFAULT);
		myParser = new Parser("English");
	}

	public World(int h, int w) throws IOException {
		height = h;
		width = w;
		myTurtle = new Turtle(TURTLE_DEFAULT);
		myParser = new Parser("English");
	}

	public World(int h, int w, Turtle t, String language) throws IOException {
		height = h;
		width = w;
		myTurtle = t;
		myParser = new Parser(language);
	}

	public abstract void fixPosition();

	// public void listen(String s) {
	// int numCmds = myParser.initializeCommands(s);
	// String param = "";
	// for (int i = 0; i < numCmds; i++)
	// param = runCommand(param);
	//
	// }

	public void listen(String s) {
		int numCmds = myParser.initializeCommands(s);
		String param = "";
		for (int i = 0; i < numCmds; i++) {
			Command c = myParser.parse(s);
			if (c.isLoop()) {
				param = "loop";
				LoopCommand loopCommand = (LoopCommand) c;
				loopCommand.readValues();
				for (double j = loopCommand.getStart() + loopCommand.getIncr(); j < loopCommand
						.getEnd(); j += loopCommand.getIncr()) {
					myParser.updateVariable(loopCommand.getVariable(), j);
					param = myTurtle.act(myParser.parse(param));
				}
			} else {
				param = myTurtle.act(myParser.parse(param));
			}
		}
	}

	// private String runCommand(String s) {
	// Command c = myParser.parse(s);
	// if (c.isLoop()) {
	// s = "loop";
	// for (double i = ((LoopCommand) c).getStart(); i < ((LoopCommand) c)
	// .getEnd(); i += ((LoopCommand) c).getIncr()) {
	//
	// }
	// }
	// return myTurtle.act(c);
	//
	// }

	public void setHeight(int h) {
		height = h;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int w) {
		width = w;
	}

	public int getWidth() {
		return width;
	}

	public void setLanguage(String language) {
		myParser.setLanguage(language);
	}

	public Turtle getTurtle() {
		return myTurtle;

	}

}
