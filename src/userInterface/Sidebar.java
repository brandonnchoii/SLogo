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

    private static final String[] BOX_LABELS = { "Turtles", "Edit Turtle", "Previous Commands",
                                                "Saved Commands" };
    private static final int SPACING = 5;

    private VBox mySidebar;
    private ComboBox<String> turtles, previous, saved, edits;
    private ArrayList<ComboBox<String>> boxes;
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

    protected void addItem (String s) {

    }

    private void initialize () {
        mySidebar = new VBox(SPACING);
        mySidebar.setPadding(new Insets(15, 15, 15, 15));
        boxes = new ArrayList<>();
        boxes.addAll(Arrays.asList(turtles, previous, saved, edits));
        for (int i = 0; i < boxes.size(); i++) {
            setUpComboBox(boxes.get(i), BOX_LABELS[i]);
        }
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
    private void addResult (String s) {
        myResults.setText(myResults.getText() + s + "\n");
    }

    private void setUpComboBox (ComboBox<String> cb, String s) {
        cb = new ComboBox<>();
        cb.setMinWidth(UserInterface.BOX_WIDTH);
        cb.setPromptText(s);
        mySidebar.getChildren().add(cb);
        // previous.setOnAction(e ->
        // System.out.println(previous.getSelectionModel().getSelectedItem()));
    }
}
