package world;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
	protected List<Turtle> myTurtles;
	protected Turtle activeTurtle;
	private Parser myParser;
	private Turtle myTurtle;

	private static final int DEFAULT_HEIGHT = 100;
	private static final int DEFAULT_WIDTH = 100;
	private static final Color TURTLE_DEFAULT = Color.BLACK;

	public World() throws IOException {
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;

		myTurtles = new ArrayList<>();
		myTurtles.add(new Turtle(TURTLE_DEFAULT, 0));
		myTurtle = new Turtle(TURTLE_DEFAULT, 0);
		myParser = new Parser("English");
		//added for testing
		myTurtle = myTurtles.get(0);
	}

	public World(int h, int w) throws IOException {
		height = h;
		width = w;
		myTurtles = new ArrayList<>();
		myTurtles.add(new Turtle(TURTLE_DEFAULT, 0));
		myTurtle = new Turtle(TURTLE_DEFAULT, 0);
		myParser = new Parser("English");
		//added for testing
		myTurtle = myTurtles.get(0);
	}

	public World(int h, int w, Turtle t, String language) throws IOException {
		height = h;
		width = w;
		myTurtles = new ArrayList<>();
		myTurtles.add(t);
		myParser = new Parser(language);
		//added for testing
		myTurtle = t;
	}

	public abstract void fixPosition();

	// public void listen(String s) {
	// int numCmds = myParser.initializeCommands(s);
	// String param = "";
	// for (int i = 0; i < numCmds; i++)
	// param = runCommand(param);
	//
	// }

	public String listen(String input) {
		String param = "";
		for (String s : input.split("/n")) {
			int numCmds = myParser.initializeCommands(s);
			for (int i = 0; i < numCmds; i++) {
				Command c = myParser.parse(param);
				System.out.println("num " + i);
				if (c.isLoop()) {
					//param = "loop";
					LoopCommand loopCommand = (LoopCommand) c;
					loopCommand.readValues();
					for (double j = loopCommand.getStart() + loopCommand.getIncr(); j < loopCommand
							.getEnd(); j += loopCommand.getIncr()) {
						param = "loop";
						myParser.updateVariable(loopCommand.getVariable(), j);
						myTurtle.act(myParser.parse(param));
					}
					myParser.resetRepcount();
				} else {
					param = myTurtle.act(c);
					//param = myTurtle.act(myParser.parse(param));
				}
			}
			param = "";
		}
		return param;		
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


	public void setLanguage(String language) {
		myParser.setLanguage(language);
	}

	public Turtle getTurtle() {
		return myTurtle;
	}
	
//	public static void main(String[] args) throws IOException {
//		BoundedWorld test = new BoundedWorld();
//		test.listen("fd 100 /n rt 90 /n fd 100");
//	}


}
