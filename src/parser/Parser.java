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
import java.util.ResourceBundle;
import java.util.Scanner;

import command.Command;
import factories.CommandFactory;

public class Parser {
	private CommandFactory myCommandFactory;
	private ResourceBundle myResources;
	private Node myTree;
	private Node myListTree;
	private String myInput;
	private int numCommands;
	private static final String LOOP = "loop";
	private static final String LOOP_DONE = "loopDone";

	public Parser(String language) {
		myCommandFactory = new CommandFactory(language);
		myResources = ResourceBundle.getBundle("resources.languages/Syntax");
	}

	public int initializeCommands(String input) {
		System.out.println(input);
		numCommands = 0;
		myTree = makeTree(new Scanner(input));

		myInput = input;
		// System.out.println(numCommands);
		return numCommands;
	}

	public Command parse(String valueFromPrevCommand) {
		System.out.println(valueFromPrevCommand);
		// Node current = getMostNestedCommand(valueFromPrevCommand);

		Node current = null;
		if (valueFromPrevCommand.equals("")) {
			current = getNodeForCommand();
		} else if (valueFromPrevCommand.equals(LOOP)) {
			System.out.println("REGISTER LOOP");
			myTree = makeTree(new Scanner(myInput));
			System.out.println(myTree.getValue());
			myTree = myListTree;
			return parse("");
		} else {
			Node previous = getNodeBeforeToReplace();
			previous.insertChild(new Node(valueFromPrevCommand, null, null),
					myResources.getString("Command"));
			current = getNodeForCommand();
		}
		List<String> commandInput = generateCommandInput(valueFromPrevCommand,
				current);
		return myCommandFactory.createCommand(commandInput);
	}

	private List<String> generateCommandInput(String valueFromPrevCommand,
			Node current) {
		ArrayList<String> commandInput = new ArrayList<String>();
		if (current == null) {
			System.out.println(valueFromPrevCommand);
			System.out.println("test");
		}
		System.out.println("current value: " + current.getValue());
		if (current.numChildren() == 2 && current != null) {
			commandInput.add(current.getValue());
			commandInput.add(current.getChild1().getValue());
			System.out.println("current child 1: "
					+ current.getChild1().getValue());
			commandInput.add(current.getChild2().getValue());
			System.out.println("current child 2: "
					+ current.getChild2().getValue());
		} else if (current.numChildren() == 1 && current != null) {
			System.out.println("current child 1: "
					+ current.getChild1().getValue());

			commandInput.add(current.getValue());
			commandInput.add(current.getChild1().getValue());
		} else {
			commandInput.add(current.getValue());
		}
		return commandInput;
	}

	private Node getNodeForCommand() {
		Node current = myTree;
		while (current != null) {
			if (current.hasChildren() && current.getChild1().isLeaf()) {
				if (current.getChild2() != null
						&& !current.getChild2().isLeaf()) {
					current = current.getChild2();
				} else {
					return current;
				}
			} else {
				current = current.getChild1();
			}
		}
		return null;
	}

	private Node getNodeBeforeToReplace() {
		Node current = myTree;
		while (current != null) {
			while (current != null) {
				if (current.getChild1().hasChildren()
						&& current.getChild1().getChild1().isLeaf()) {
					if (current.getChild1().getChild2() != null
							&& !current.getChild1().getChild2().isLeaf()) {
						current = current.getChild1();
					} else {
						return current;
					}
				} else if (current.getChild2() != null
						&& current.getChild2().hasChildren()
						&& current.getChild2().getChild1().isLeaf()) {
					if (current.getChild2().getChild2() != null
							&& !current.getChild2().getChild2().isLeaf()) {
						current = current.getChild2();
					} else {
						return current;
					}
				} else {
					current = current.getChild1();
				}
			}
		}
		return null;
	}

	private Node makeTree(Scanner input) {
		String current = input.next();

		if (current.matches(myResources.getString("ListStart"))) {
			current = input.next();
			String loopString = "";
			while (!current.matches(myResources.getString("ListEnd"))) {
				loopString += current + " ";
				current = input.next();
			}
			Scanner loopScanner = new Scanner(loopString);
			if (loopScanner.next().matches(myResources.getString("Command"))) {
				myListTree = makeTree(new Scanner(loopString));
				return makeTree(new Scanner(loopString));
			} else {
				myCommandFactory.initializeLoopVariables(loopString);
				return new Node(loopString, null, null);
			}
		} else if (current.matches(myResources.getString("Constant"))
				|| current.matches(myResources.getString("Variable"))) {
			return new Node(current, null, null);
		} else if (current.matches(myResources.getString("Command"))) {
			numCommands += 1;
			int numChildren = myCommandFactory.getNumParameters(current);
			// System.out.println(numChildren);
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
	
	public void setLanguage(String language) {
		myCommandFactory.setLanguage(language);
	}
	
	public static void main(String[] args) throws IOException {
		Parser test = new Parser("English");
		test.initializeCommands("repeat 2 [ sum 1 2 ]");
		test.parse("");
		test.parse("3");
//		test.parse("21");
		test.parse("loop");
//		test.parse("11");
//		test.parse("21");
//		test.parse("loop");
//		test.parse("11");
//		test.parse("21");
	}

	public void updateVariable(String variable, double variableValue) {
		myCommandFactory.updateVariable(variable, variableValue);
	}
}
