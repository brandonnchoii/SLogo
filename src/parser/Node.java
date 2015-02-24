package parser;

/**
<<<<<<< HEAD
 * The Node class is for creating trees
=======
 * This class is responsible for parsing the user inputted commands and using
 * the CommandFactory to generate Commands
>>>>>>> 727601d81786c0f2d9eae273f0affe0853b8e92b
>>>>>>> c41bbd8d7daa67a14644574cf954b0dc387daf2c
 * 
 * @author Megan
 *
 */
public class Node {
	private String myValue;
	private Node myChild1;
	private Node myChild2;

	public Node(String value, Node child1, Node child2) {
		myValue = value;
		myChild1 = child1;
		myChild2 = child2;
	}

	
	public String getValue() {
		return myValue;
	}
	
	public void addChild(Node newNode) {
		if (myChild1 == null) {
			myChild1 = newNode;
		}
		else if (myChild2 == null) {
			myChild2 = newNode;
		}
	}
	

	public int numChildren() {
		if (myChild2 != null) {
			return 2;
		} else if (myChild1 != null) {
			return 1;
		}
		return 0;
	}

	public boolean hasChildren() {
		return myChild1 != null || myChild2 != null;
	}
	
	public Node getChild1() {
		return myChild1;
	}
	
	public Node getChild2() {
		return myChild2;
	}
	
	public boolean isLeaf() {
		return myChild1 ==  null && myChild2 == null;
	}

	public void insertChild(Node newNode, String command) {
		if (myChild2 != null && myChild2.getValue().matches(command)
				&& myChild1 != null && !myChild1.getValue().matches(command)) {
			myChild2 = newNode;
		} else {
			myChild1 = newNode;
		}
	}
}
