package parser;

/**
 * This class is responsible for parsing the user inputted 
 * commands and using the CommandFactory to generate Commands
 * 
 * @author Megan
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.paint.Color;
import command.Command;
import command.CommandFactory;

public class Parser {
    private CommandFactory myCommandFactory;
    private ResourceBundle myResources;
    private Node myTree;
    private Node myListTree;
    private String myInput;
    private int numCommands;
    private static final String LIST = "list";
    private ObservableMap<String, Double> variables;
    private ObservableMap<String, String> functions;
    private List<ObjectProperty> bindings;
    private static ObservableList<Color> colors;

    public Parser(String language, ObservableMap<String, Double> var, ObservableMap<String, String> functions2, List<ObjectProperty> bind, ObservableList<Color> color) {
        variables = var;
        functions = functions2;
        bindings = bind;
        colors = color;
        myCommandFactory = new CommandFactory(language, variables, functions, bindings, colors);
        myResources = ResourceBundle.getBundle("resources.languages/Syntax");
    }
    
    /**
     * Initializes the tree based on the user input
     * 
     * @param input
     * @return int numCommands
     */

    public int initializeCommands(String input) {
        numCommands = 0;
        myTree = makeTree(new Scanner(input));
        myInput = input;
        // if (myListTree == null) {
        // myListTree = makeTree(new Scanner(input));
        // numCommands = numCommands / 2;
        // }
        return numCommands;
    }
    
    /**
     * Replaces value from the previously executed command in 
     * the tree and retrieves the next command to be executed.
     * 
     * @param valueFromPrevCommand
     * @return Command
     */

    public Command parse(String valueFromPrevCommand) {
        System.out.println("prevValue = " + valueFromPrevCommand);
        Node current = null;
        System.out.println("mytree = " +  myTree.getValue());
        if (valueFromPrevCommand.equals("")) {
            current = getNodeForCommand();
        } else if (valueFromPrevCommand.equals(LIST)) {
            myTree = makeTree(new Scanner(myInput));
            myTree = myListTree;
            current = getNodeForCommand();
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
    
    /**
     * Returns a list containing the elements (value of node of most nested 
     * command and its children) needed to pass into myCommandFactory to 
     * generate a command. 
     * 
     * @param  String valueFromPrevCommand
     * @param  Node current
     * @return List<String>
     */

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
    
    /**
     * Traverses the tree and returns the deepest (most nested) command.
     * 
     * @return Node
     */

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
            } else if (!current.hasChildren()) {
                return current;
            } else {
                current = current.getChild1();
            }
        }
        return null;
    }
    
    /**
     * Traverses the tree and retrieves the node that is the parent of
     * the most nested command in order to replace that with the value
     * of the most nested command executed.
     * 
     * @return Node
     */

    private Node getNodeBeforeToReplace() {
        Node current = myTree;
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
                if (current.getChild1().getValue()
                        .matches(myResources.getString("Command"))
                        || (current.getChild2() != null && !current.getChild2()
                        .getValue()
                        .matches(myResources.getString("Command")))) {
                    current = current.getChild1();
                } else {
                    current = current.getChild2();
                }
            }
        }
        return null;
    }
    
    /**
     * Creates tree out of Nodes using regex. Constants and
     * variables are leaf children of commands. If a list is 
     * detected, creates anothr tree for the contents of the list.
     * 
     * @param input
     * @return Node
     */

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
                numCommands -= 1;
                return null;
            } else {
                return new Node(loopString, null, null);
            }
        } else if (current.matches(myResources.getString("Constant"))
                || current.matches(myResources.getString("Variable"))) {
            return new Node(current, null, null);
        } else if (current.matches(myResources.getString("Command"))) {
            numCommands += 1;
            int numChildren = myCommandFactory.getNumParameters(current);
            if (numChildren == 1) {
                Node newChild = makeTree(input);
                return new Node(current, newChild, null);
            } else if (numChildren == 2) {
                Node newChild1 = makeTree(input);
                Node newChild2 = makeTree(input);
                return new Node(current, newChild1, newChild2);
            } else {
                return new Node(current, null, null);
            }
        }
        return null;
    }
    
    /**
     * Ability to change language of input
     * @param language
     */

    public void setLanguage(String language) {
        myCommandFactory.setLanguage(language);
    }

    public static void main(String[] args) {
        ObservableMap<String, Double> variables = null;
        ObservableMap<String, String> functions = null;
        List<ObjectProperty> bindings = null;
        ObservableList<Color> colors = null;
        Parser test = new Parser("English", variables, functions, bindings, colors);
        test.initializeCommands("repeat 2 [ sum 1 2 ]");
        test.parse("");
        test.parse("3");
        // test.parse("21");
        test.parse("loop");
        // test.parse("11");
        // test.parse("21");
        // test.parse("loop");
        // test.parse("11");
        // test.parse("21");
    }

    /**
     * Calls method in myCommandFactory to update the variablecount in a loop.
     * @param variable
     * @param variableValue
     */
    
	public void updateVariable(String variable, double variableValue) {
		myCommandFactory.updateVariable(variable, variableValue);
	}

    public void resetRepcount() {
        myCommandFactory.resetRepcount();
    }

}
