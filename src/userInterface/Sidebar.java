package userInterface;

import java.util.ResourceBundle;

import turtle.Turtle;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class Sidebar {

    private static String[] boxes = { "Turtles", "Previous", "Saved"};

    private VBox mySidebar;
    private ComboBox<Turtle> turtles;
    private ComboBox<String> previous;
    private ComboBox<Button> saved;

    public Sidebar () {
        mySidebar = new VBox(30);
        initialize();
    }

    public VBox getSidebar () {
        return mySidebar;
    }

    private void initialize () {
        for (int i = 0; i < boxes.length; i++) {
            try{
                ComboBox cb = createComboBox(i);
                cb.setMinWidth(180);
                mySidebar.getChildren().add(cb);
            }
            catch (NullPointerException e){
                System.out.println("Null pointer exception!"); //maybe make into a popup later
            }
        }
    }

    private ComboBox createComboBox (int i) {
        if (i == 0) {
            turtles = new ComboBox<Turtle>();
            turtles.setPromptText("Turtles");
            return turtles;
        }
        else if (i == 1) {
            previous = new ComboBox<String>();
            previous.setPromptText("Previous Commands");
            return previous;
        }
        else if (i == 2) {
            saved = new ComboBox<Button>();
            saved.setPromptText("Saved Commands");
            return saved;
        }
        return null;
    }

    protected void addTurtle (Turtle t) {
       turtles.getItems().add(t);
    }

    protected void addPrevious (String s) {
        previous.getItems().add(s);
    }

    protected void addSaved (Button b) {
        saved.getItems().add(b);
    }

}
