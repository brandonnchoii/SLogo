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

private static final String DEFAULT_WHITE_BG = "-fx-background: white;";

private static final String PLAY_BUTTON = "resources/images/PlayButton.jpg";

private static final int RIGHT_PANEL_SPACING = 40;

private static final String DEFAULT_RESULT = "Results: \n\n";
    
    private VBox myRightPanel;
    private ScrollPane myResultView;
    private Text myResults;
    private EventHandler<ActionEvent> runCommand;
    
    public RightPanel (EventHandler<ActionEvent> run) {
        runCommand = run;
        initialize();
    }
    
    public VBox getPanel() {
        return myRightPanel;
    }

    protected void addResult (String s) {
        myResults.setText(myResults.getText() + s + "\n");
    }
    
    private void initialize () {
        myRightPanel = new VBox(RIGHT_PANEL_SPACING);
        myResultView = new ScrollPane();
        myResultView.setStyle(DEFAULT_WHITE_BG);
        myResultView.setMinSize(UI.PANEL_WIDTH, UI.PANEL_HEIGHT);
        myResults = new Text(DEFAULT_RESULT);
        myResultView.setContent(myResults);
        myRightPanel.getChildren().addAll(myResultView, makeRunButton());
    }
        
    private Button makeRunButton () {
        Button play = new Button();
        Image playImage = new Image(PLAY_BUTTON);
        ImageView playView = new ImageView(playImage);
        play.setGraphic(playView);   
        play.setOnAction(runCommand);
        return play;
    }
}
