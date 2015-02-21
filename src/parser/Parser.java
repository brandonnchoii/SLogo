package parser;

/**
 * This class is responsible for parsing the user inputted 
 * commands and using the CommandFactory to generate Commands
 * 
 * @author Megan
 *
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Scanner;

import command.Command;
import factories.CommandFactory;

public class Parser {
	private CommandFactory myCommandFactory;
	private ResourceBundle myResources;
	private Node myTree;

	public Parser(String language) throws IOException {
		myCommandFactory = new CommandFactory(language);
		myResources = ResourceBundle.getBundle("resources.language/Syntax");
	}

	public void initializeCommands(String input) {
		Scanner scanInput = new Scanner(input);
		myTree = makeTree(scanInput);
	}
	
	public Queue<Command> parse(String input) {
		//Queue<Command> commandQueue = new Queue<Command>();
	
		Node current = myTree;
		while(current != null) {
			ArrayList<String> commandInput = new ArrayList<String>();
			if(current.hasChildren() && current.getChild1().isLeaf()) {
				if (current.getChild2() != null && current.getChild2().isLeaf()) {
					commandInput.add(current.getValue());
					commandInput.add(current.getChild1().getValue());
					commandInput.add(current.getChild2().getValue());
				}
				else {
					commandInput.add(current.getValue());
					commandInput.add(current.getChild1().getValue());
				}
				Command command = myCommandFactory.createCommand(commandInput);
				//commandQueue.add(command);
				current = new Node(command.getValue(), null, null);
			}
		}
		//traverse
		//if children are leaves, create command from current and leaves, put into queue
		//return value that replaces that node
		
		
		return null;
	}

	private Node makeTree(Scanner input) {
		String current = input.next();
		if (current.matches(myResources.getString("constant"))
				|| current.matches(myResources.getString("variable"))) {
			return new Node(current, null, null);
		} else if (current.matches(myResources.getString("command"))) {
			int numChildren = myCommandFactory.getNumParameters(current);
			if (numChildren == 1) {
				Node newChild = makeTree(input);
				return new Node(current, newChild, null);
			} else if (numChildren == 2) {
				Node newChild1 = makeTree(input);
				Node newChild2 = makeTree(input);
				return new Node(current, newChild1, newChild2);
			}
		}
		return null;
	}

}
