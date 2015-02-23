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
	private int numCommands;

	public Parser(String language) throws IOException {
		myCommandFactory = new CommandFactory(language);
		myResources = ResourceBundle.getBundle("resources.languages/Syntax");
	}

	public int initializeCommands(String input) {
		numCommands = 0;
		Scanner scanInput = new Scanner(input);
		myTree = makeTree(scanInput);
		return numCommands;
	}
	
	public Command parse(String valueFromPrevCommand) {
		//Queue<Command> commandQueue = new Queue<Command>();
		ArrayList<String> commandInput = new ArrayList<String>();
		Node current = null;
		if (valueFromPrevCommand.equals("")) {
			current = getNode();		}
		else {
			Node previous = getNode();
			previous = new Node(valueFromPrevCommand, null, null);
			current = getNode();
		}
		System.out.println("current value: " + current.getValue());
		if (current.numChildren() == 2 && current != null) {
			commandInput.add(current.getValue());
			commandInput.add(current.getChild1().getValue());
			System.out.println("current child 1: " + current.getChild1().getValue());
			commandInput.add(current.getChild2().getValue());
			System.out.println("current child 2: " + current.getChild2().getValue());
		}
		else if (current.numChildren() == 1 && current != null) {
			commandInput.add(current.getValue());
			commandInput.add(current.getChild1().getValue());
		}
		else {
			commandInput.add(current.getValue());
		}
		
		
		return myCommandFactory.createCommand(commandInput);
	}

	private Node getNode() {
		Node current = myTree;
		while(current != null) {
			//System.out.println(current.getValue());
			//ArrayList<String> commandInput = new ArrayList<String>();
			if(current.hasChildren() && current.getChild1().isLeaf()) {
				if (current.getChild2() != null && !current.getChild2().isLeaf()) {
					//System.out.println("hi");
					current = current.getChild2();
				}
				else {
					//System.out.println("hi1");
					return current;
				}
				//this is where long comment went if I need it *reminder for myself*
			}
			else {
				//System.out.println("hi2");
				current = current.getChild1();
			}
		}
		return null;
	}
	
	private Node makeTree(Scanner input) {
		String current = input.next();
		//System.out.println(current);
		if (current.matches(myResources.getString("Constant"))
				|| current.matches(myResources.getString("Variable"))) {
			return new Node(current, null, null);
		} else if (current.matches(myResources.getString("Command"))) {
			numCommands += 1;
			int numChildren = myCommandFactory.getNumParameters(current);
			//System.out.println(numChildren);
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

	 public static void main (String [] args) throws IOException {	        
			Parser test = new Parser("English");
			test.initializeCommands("fd sum 10 sum 5 6");
			test.parse("");
	 }
}
