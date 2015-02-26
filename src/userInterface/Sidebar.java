/**
 *
 * @author Brandon Choi
 *
 */

package userInterface;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Sidebar {

    public static final int TURTLE_INDEX = 0;
    public static final int EDITS_INDEX = 1;
    public static final int PREVIOUS_INDEX = 2;
    public static final int SAVED_INDEX = 3;

    private static final String[] BOX_LABELS = { "Turtles", "Edit Turtle", "Previous Commands",
                                                "Saved Commands" };
    private static final int SPACING = 5;

    private VBox mySidebar;
    private ComboBox<String> turtles, edits, previous, saved;
    protected ArrayList<ComboBox<String>> boxes;
    private ScrollPane myResultBox;
    private Text myResults;

    public Sidebar () {
        initialize();
    }

    public VBox getSidebar () {
        return mySidebar;
    }

    public void add (Node n) {
        mySidebar.getChildren().add(n);
    }

    protected void addItem (String s, int index) {
        boxes.get(index).getItems().add(s);
    }

    private void initialize () {
        mySidebar = new VBox(SPACING);
        mySidebar.setPadding(new Insets(15, 15, 15, 15));
        boxes = new ArrayList<ComboBox<String>>();
        Arrays.asList(turtles, edits, previous, saved).stream().forEach(e -> {
            e = new ComboBox<>();
            boxes.add(e);
        });
        boxes.stream().forEach(e -> setUpComboBox(e, BOX_LABELS[boxes.indexOf(e)]));
        setUpResults();
        setUpVariableEditor();
    }

    private void setUpResults () {
        myResultBox = new ScrollPane();
        myResults = new Text("Results: \n\n");
        myResultBox.setStyle("-fx-background: white;");
        myResultBox.setMinSize(UserInterface.BOX_WIDTH, UserInterface.BOX_WIDTH);
        myResultBox.setContent(myResults);
        mySidebar.getChildren().add(myResultBox);
    }

    private void setUpVariableEditor () {

    }

    // private vs protected vs public?
    protected void addResult (String s) {
        myResults.setText(myResults.getText() + s + "\n");
    }

    private void setUpComboBox (ComboBox<String> cb, String s) {
        cb.setMinWidth(UserInterface.BOX_WIDTH);
        cb.setPromptText(s);
        mySidebar.getChildren().add(cb);
        // previous.setOnAction(e ->
        // System.out.println(previous.getSelectionModel().getSelectedItem()));
    }
}
