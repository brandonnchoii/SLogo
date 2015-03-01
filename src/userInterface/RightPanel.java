package userInterface;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RightPanel {

private static final String DEFAULT_RESULT = "Results: \n\n";
    
    private VBox myRightPanel;
    private ScrollPane myResultView;
    private Text myResults;
    
    public RightPanel () {
        initialize();
    }
    
    public VBox getPanel() {
        return myRightPanel;
    }

    private void initialize () {
        myRightPanel = new VBox();
        myRightPanel.setPadding(new Insets(10, 10, 10, 10));
        myResultView = new ScrollPane();
        myResultView.setStyle("-fx-background: white;");
        myResultView.setMinSize(UI.PANEL_WIDTH, UI.PANEL_HEIGHT);
        myResults = new Text(DEFAULT_RESULT);
        myResultView.setContent(myResults);
        myRightPanel.getChildren().add(myResultView);
    }
    
    public void addResult (String s) {
        myResults.setText(myResults.getText() + s + "\n");
    }
}
