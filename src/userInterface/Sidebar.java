package userInterface;

import turtle.Turtle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class Sidebar {

    private static final String[] boxes = { "Turtles", "Edit Turtle", "Previous Commands", "Saved Commands" };
    private static final int BOX_WIDTH = 180;
    private static final int SPACING = 30;

    private VBox mySidebar;
    private ComboBox<Turtle> turtles;
    private ComboBox<String> previous;
    private ComboBox<Button> saved;
    private ComboBox<String> edits;

    public Sidebar () {
        mySidebar = new VBox(SPACING);
        initialize();
    }

    public VBox getSidebar () {
        return mySidebar;
    }

    public void add (Node n) {
        mySidebar.getChildren().add(n);
    }

    protected void addNewTurtle (Turtle t) { // change to string
        turtles.getItems().add(t);
    }

    protected void addNewPrevious (String s) {
        previous.getItems().add(s);
    }

    protected void addNewSaved (Button b) {
        saved.getItems().add(b);
    }

    private void initialize () {
        for (int i = 0; i < boxes.length; i++) {
            try {
                ComboBox cb = createComboBox(i, boxes[i]);
                cb.setMinWidth(BOX_WIDTH);
                mySidebar.getChildren().add(cb);
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception!"); // maybe make into a popup later
            }
        }
    }

    // refactor to make this cleaner
    private ComboBox createComboBox (int i, String s) {
        if (i == 0) {
            turtles = new ComboBox<>();
            turtles.setPromptText(s);
            return turtles;
        }
        else if (i == 1) {
            edits = new ComboBox<>();
            edits.setPromptText(s);
            return edits;
        }
        else if (i == 2) {
            previous = new ComboBox<>();
            previous.setPromptText(s);
            return previous;
        }
        else if (i == 3) {
            saved = new ComboBox<>();
            saved.setPromptText(s);
            return saved;
        }
        return null;
    }
    
    
}
