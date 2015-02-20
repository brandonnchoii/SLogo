package parser;

/**
 * This class is responsible for parsing the user inputted 
 * commands and using the CommandFactory to generate Commands
 * 
 * @author Megan
 *
 */

import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;

import command.Command;
import factories.CommandFactory;

public class Parser {
	private CommandFactory myCommandFactory;
	private ResourceBundle myResources;

	public Parser() {
		myCommandFactory = new CommandFactory();
		myResources = ResourceBundle.getBundle("resources.language/Syntax");
	}

	public Queue<Command> parse(String input) {
		for (String s: input.split(" ")) {
			if (s.matches(myResources.getString("command"))) {
				
			}
		}
		return null;
	}
}
