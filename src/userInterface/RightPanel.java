package userInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RightPanel {

    private static final int DOUBLE_CLICK = 2;
    private static final String RESULTS_LABEL = "Results";
    private static final String SAVED_COMMANDS_LABEL = "Saved Commands";
    private static final String PREVIOUS_COMMANDS_LABEL = "Previous Commands";
    private static final int RESULTS_VIEW_HEIGHT = 120;
    private static final String DEFAULT_WHITE_BG = "-fx-background: white;";
    private static final int RIGHT_PANEL_SPACING = 10;

    private static RightPanel instance;
    private VBox myRightPanel;
    private ScrollPane myResultView;
    private Text myResults;
    private ObservableList<String> results;
    private ObservableList<String> previousCommands;
    private ObservableList<String> savedCommands;
    private ListView<String> resultsView;
    private ListView<String> previousCommandView;
    private ListView<String> savedCommandView;

    public RightPanel () {}

    public static synchronized RightPanel getInstance () {
        if (instance == null) {
            instance = new RightPanel();
            return instance;
        }
        else
            return instance;
    }

    public VBox getPanel () {
        return myRightPanel;
    }

    public void initialize (ObservableList<String> r, ObservableList<String> p, ObservableList<String> s) {
        results = r;
        previousCommands = p;
        savedCommands = s;
        
        myRightPanel = new VBox(RIGHT_PANEL_SPACING);
        myRightPanel.setPrefSize(UI.PANEL_WIDTH, UI.PANEL_HEIGHT);

        resultsView = new ListView<>();

        previousCommandView = new ListView<>();
        previousCommandView.setOnMouseClicked(e -> {
            // if (e.getClickCount() == DOUBLE_CLICK)
            });

        savedCommandView = new ListView<>();
        savedCommandView.setOnMouseClicked(e -> {
            // if (e.getClickCount() == DOUBLE_CLICK)
            });

         myRightPanel.getChildren().addAll(makeListView(resultsView, results, RESULTS_LABEL),
         makeListView(previousCommandView, previousCommands, PREVIOUS_COMMANDS_LABEL),
         makeListView(savedCommandView, savedCommands, SAVED_COMMANDS_LABEL));
    }

    private VBox makeListView (ListView view, ObservableList list, String name) {
        VBox listViewBox = new VBox();
        listViewBox.setAlignment(Pos.CENTER);
        Label label = new Label(name);
        view.setItems(list);
        listViewBox.getChildren().addAll(label, view);
        return listViewBox;
    }
}
