package userInterface;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;


public class RightPanel {

    private static final int DOUBLE_CLICK = 2;
    private static final String RESULTS_LABEL = "Results";
    private static final String SAVED_COMMANDS_LABEL = "Saved Commands";
    private static final String PREVIOUS_COMMANDS_LABEL = "Previous Commands";
    private static final int RIGHT_PANEL_SPACING = 10;

    private static RightPanel instance;
    private VBox myRightPanel;
    private ObservableList<String> results;
    private ObservableList<String> previousCommands;
    private ObservableList<String> savedCommands;
    private ListView<String> resultsView;
    private ListView<String> previousCommandView;
    private ListView<String> savedCommandView;
    private StringProperty inputText;

    public RightPanel () {
    }

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

    public void initialize (ObservableList<String> r,
                            ObservableList<String> p,
                            ObservableList<String> s,
                            StringProperty t) {
        results = r;
        previousCommands = p;
        savedCommands = s;
        inputText = t;

        myRightPanel = new VBox(RIGHT_PANEL_SPACING);
        myRightPanel.setPrefSize(UI.PANEL_WIDTH, UI.PANEL_HEIGHT);
        resultsView = new ListView<>();
        previousCommandView = new ListView<>();
        previousCommandView.setOnMouseClicked(e -> {
            if (e.getClickCount() == DOUBLE_CLICK)
                inputText.setValue(previousCommandView.getSelectionModel().getSelectedItem());
        });
        savedCommandView = new ListView<>();
        savedCommandView.setOnMouseClicked(e -> {
            if (e.getClickCount() == DOUBLE_CLICK)
                inputText.setValue(savedCommandView.getSelectionModel().getSelectedItem());
        });

        myRightPanel.getChildren().addAll(makeListView(resultsView, results, RESULTS_LABEL),
                                          makeListView(previousCommandView, previousCommands,
                                                       PREVIOUS_COMMANDS_LABEL),
                                          makeListView(savedCommandView, savedCommands,
                                                       SAVED_COMMANDS_LABEL));
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
