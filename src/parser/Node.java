package parser;

/**
 * This class is responsible for parsing the user inputted commands and using
 * the CommandFactory to generate Commands
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
		} else if (myChild2 == null) {
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
		return myChild1 == null && myChild2 == null;
	}

	public void insertChild(Node newNode) {
		if (myChild1 == null) {
			myChild1 = newNode;
		} else {
			myChild2 = newNode;
		}
	}
}
