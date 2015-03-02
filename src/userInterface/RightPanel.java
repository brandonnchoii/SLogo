package userInterface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RightPanel {

private static final int RIGHT_PANEL_SPACING = 40;

private static final String DEFAULT_RESULT = "Results: \n\n";
    
    private VBox myRightPanel;
    private ScrollPane myResultView;
    private Text myResults;
    
    public RightPanel (EventHandler<ActionEvent> run) {
        initialize(run);
    }
    
    public VBox getPanel() {
        return myRightPanel;
    }

    protected void addResult (String s) {
        myResults.setText(myResults.getText() + s + "\n");
    }
    
    private void initialize (EventHandler<ActionEvent> event) {
        myRightPanel = new VBox(RIGHT_PANEL_SPACING);
        myResultView = new ScrollPane();
        myResultView.setStyle("-fx-background: white;");
        myResultView.setMinSize(UI.PANEL_WIDTH, UI.PANEL_HEIGHT);
        myResults = new Text(DEFAULT_RESULT);
        myResultView.setContent(myResults);
        myRightPanel.getChildren().addAll(myResultView, makeRunButton(event));
    }
        
    private Button makeRunButton (EventHandler<ActionEvent> event) {
        Button play = new Button();
        Image playImage = new Image("resources/images/PlayButton.jpg");
        ImageView playView = new ImageView(playImage);
        play.setGraphic(playView);   
        play.setOnAction(event);
        return play;
    }
}
