/**
 * @author Brandon Choi, James Mosca
 */

package userInterface;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import worldController.WorldController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    /**
     * UI represents the user interface of a single instance of SLogo. Multiple instances can be
     * added by creating a new tab in UIManager
     */

    public static final int PANEL_WIDTH = 200;
    public static final int PANEL_HEIGHT = 500;
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
    private StringProperty inputText;
    private List<ObjectProperty> myBindings;
    private ObjectProperty<ResourceBundle> myLanguage;
    private ObjectProperty<String> myCanvasColor;

    public UI (List<ObjectProperty> bindings) {
        initialize(bindings);
    }

    /**
     * @return BorderPane myView
     */
    public Pane getUI () {
        return myView;
    }

    /**
     * initializes the UI by creating border panes and setting up necessary components such as the
     * command field
     * 
     * @param List<ObjectProperty> bindings
     */
    private void initialize (List<ObjectProperty> bindings) {
        myBindings = bindings;
        myLanguage = myBindings.get(UIManager.LANGUAGE_INDEX);
        myCanvasColor = myBindings.get(UIManager.CANVAS_INDEX);

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

    /**
     * sets up the WorldController
     */
    private void setUpController () {
        try {
            myController = new WorldController(this, myRightPanel, myLeftPanel, inputText,
                                               myBindings);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * sets up the command field which will be cleared the first time it is clicked and whenever it
     * is played
     * 
     * binding between inputText and myInput allows the user to double click a saved or previous
     * command and have it appear in the command box
     */
    private void setUpCommandField () {
        myCommandField = new HBox(15);
        myInput = new TextArea(myLanguage.getValue().getString("InputText"));

        inputText = new SimpleStringProperty();
        inputText = myInput.textProperty();

        firstClick = true;
        myInput.setMaxSize(INPUT_WIDTH, INPUT_HEIGHT);
        myInput.setOnMouseClicked(e -> {
            if (firstClick) {
                myInput.clear();
                firstClick = false;
            }
        });

        myCommandField.setAlignment(Pos.CENTER);
        myCommandField.getChildren().addAll(myInput, makeRunButton());
    }

    /**
     * creates run button
     * @return Button play
     */
    private Button makeRunButton () {
        Button play = new Button();
        Image playImage = new Image(PLAY_BUTTON);
        ImageView playView = new ImageView(playImage);
        play.setGraphic(playView);
        play.setOnAction(e -> runCommand());
        return play;
    }

    /**
     * sets up the canvas pane with the canvas and graphics context
     */
    private void setUpPane () {
        canvasPane = new StackPane();
        canvasPane.setPadding(new Insets(10, 10, 10, 10));
        myCanvas = new Canvas();
        myGC = myCanvas.getGraphicsContext2D();
        canvasPane.getChildren().add(myCanvas);
        canvasPane.styleProperty().bind(myCanvasColor);
    }
    
    /**
     * called to run whatever command(s) are in the command field
     */
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
