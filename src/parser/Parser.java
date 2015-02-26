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
	private static Node myTreeCopy;
	private int numCommands;
	private static final String LOOP = "loop";

	public Parser(String language) throws IOException {
		myCommandFactory = new CommandFactory(language);
		myResources = ResourceBundle.getBundle("resources.languages/Syntax");
	}

	public int initializeCommands(String input) {
		numCommands = 0;
		if (input.contains(myResources.getString("ListStart"))) {
			String loopString = input.substring(
					input.indexOf(myResources.getString("ListStart")),
					input.indexOf(myResources.getString("ListEnd")));
			System.out.println(loopString);
			Node loop = makeTree(new Scanner(loopString));
			// String loop = input.substring(beginIndex, endIndex)
		}
		myTree = makeTree(new Scanner(input));
		myTreeCopy = makeTree(new Scanner(input));
		//System.out.println(numCommands);
		return numCommands;
	}

	public Command parse(String valueFromPrevCommand) {
		// Queue<Command> commandQueue = new Queue<Command>();
		ArrayList<String> commandInput = new ArrayList<String>();
		System.out.println(valueFromPrevCommand);
		//Node current = getMostNestedCommand(valueFromPrevCommand);
		
		Node current = null;
		if (valueFromPrevCommand.equals("")) {
			current = getNodeForCommand();
		} else if (valueFromPrevCommand.equals(LOOP)) {
			myTree = myTreeCopy;
			return parse("");
		} else {
			Node previous = getNodeBeforeToReplace();
			// System.out.println(previous.getChild1().getValue() +
			// previous.getChild2().getValue());
			previous.insertChild(new Node(valueFromPrevCommand, null, null),
					myResources.getString("Command"));
			// System.out.println(previous.getChild1().getValue() +
			// previous.getChild2().getValue());
			current = getNodeForCommand();
		}
		
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

		return myCommandFactory.createCommand(commandInput);
	}

//	private Node getMostNestedCommand(String valueFromPrevCommand) {
//		Node current = null;
//		if (valueFromPrevCommand.equals("")) {
//			current = getNodeForCommand();
//		} else if (valueFromPrevCommand.equals(LOOP)) {
//			myTree = myTreeCopy;
//			parse("");
//		} else {
//			Node previous = getNodeBeforeToReplace();
//			// System.out.println(previous.getChild1().getValue() +
//			// previous.getChild2().getValue());
//			previous.insertChild(new Node(valueFromPrevCommand, null, null),
//					myResources.getString("Command"));
//			// System.out.println(previous.getChild1().getValue() +
//			// previous.getChild2().getValue());
//			current = getNodeForCommand();
//		}
//		return current;
//	}

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
		//System.out.println(current);
		if (current.matches(myResources.getString("ListStart"))) {
			current = input.next();
			String loopString = "";
			while (!current.matches(myResources.getString("ListEnd"))) {
				loopString += current + " ";
				current = input.next();
			}
			System.out.println(loopString);
			return makeTree(new Scanner(loopString));
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

	public static void main(String[] args) throws IOException {
		Parser test = new Parser("English");
		test.initializeCommands("fd sum 10 sum 5 6");
		test.parse("");
		test.parse("11");
		test.parse("21");
		test.parse("loop");
		test.parse("11");
		test.parse("21");
		test.parse("loop");
		test.parse("11");
		test.parse("21");
	}
}
