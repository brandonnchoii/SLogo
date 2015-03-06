package userInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

import worldController.WorldController;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class UI {

    public static final int PANEL_WIDTH = 200;
    public static final int PANEL_HEIGHT = 500;
    private static final String DEFAULT_BACKGROUND_COLOR = "-fx-background-color: white";
    private static final String PLAY_BUTTON = "resources/images/PlayButton.jpg";
    private static final int INPUT_WIDTH = 450;
    private static final int INPUT_HEIGHT = 300;

    private WorldController myController;
    private BorderPane myView, myCenterView;
    private HBox myCommandField;
    private TextArea myInput;
    private boolean firstClick;
    private RightPanel myRightPanel;
    private LeftPanel myLeftPanel;
    private Canvas myCanvas;
    private GraphicsContext myGC;
    private StackPane canvasPane;
    private ResourceBundle myLanguage;
    private StringProperty inputText;

    public UI (ResourceBundle language) {
        initialize(language);
    }

    public Pane getUI () {
        return myView;
    }

    private void initialize (ResourceBundle language) {
        myLanguage = language;
        myView = new BorderPane();
        myView = new BorderPane();
        myView.setPadding(new Insets(10, 10, 10, 10));
        myCenterView = new BorderPane();
        myCenterView.setPadding(new Insets(5, 5, 5, 5));

        setUpCommandField();
        setUpPane();
        setUpController();

        myView.setCenter(myCenterView);
        myView.setRight(myRightPanel.getInstance().getPanel());
        myView.setLeft(myLeftPanel.getInstance().getPanel());
        myCenterView.setCenter(canvasPane);
        myCenterView.setBottom(myCommandField);
        myCenterView.setAlignment(myInput, Pos.CENTER);
    }

    private void setUpController () {
        try {
            myController = new WorldController(this, myRightPanel, myLeftPanel, inputText);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpCommandField () {
        myCommandField = new HBox(15);
        myInput = new TextArea(myLanguage.getString("InputText"));
        
        inputText = new SimpleStringProperty();
        inputText = myInput.textProperty();

        firstClick = true;
        myInput.setMaxSize(INPUT_WIDTH, INPUT_HEIGHT);
        myInput.setOnMouseClicked(e -> {
            if (firstClick){
                myInput.clear();
                firstClick = false;
            }   
        });
        
        myCommandField.setAlignment(Pos.CENTER);
        myCommandField.getChildren().addAll(myInput, makeRunButton());
    }

    private Button makeRunButton () {
        Button play = new Button();
        Image playImage = new Image(PLAY_BUTTON);
        ImageView playView = new ImageView(playImage);
        play.setGraphic(playView);
        play.setOnAction(e -> runCommand());
        return play;
    }

    private void setUpPane () {
        canvasPane = new StackPane();
        canvasPane.setPadding(new Insets(10, 10, 10, 10));
        myCanvas = new Canvas();
        myGC = myCanvas.getGraphicsContext2D();
        canvasPane.getChildren().add(myCanvas);
        canvasPane.setStyle(DEFAULT_BACKGROUND_COLOR);
    }

    private void runCommand () {
        myController.update(inputText.getValue());
        myInput.clear();
    }

    public GraphicsContext getGraphics () {
        return myGC;
    }

    public Canvas getCanvas () {
        return myCanvas;
    }

    public StackPane getPane () {
        return canvasPane;
    }
}
