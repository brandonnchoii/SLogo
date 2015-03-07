/**
 * @author Brandon Choi
 */

package userInterface;

import java.io.IOException;
import java.util.ResourceBundle;

import turtle.Turtle;
import worldController.WorldController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UIManager {

    /**
     * UIManager can hold multiple tabs of SLogo and holds the menu bar that controls features such
     * as the background color, turtle image, etc.
     */

    public static final int UI_WIDTH = 1000;
    public static final int UI_HEIGHT = 750;
    public static final int CANVAS_INDEX = 0;
    public static final int PEN_COLOR_INDEX = 1;
    public static final int PEN_SIZE_INDEX = 2;
    public static final int TURTLE_IMAGE_INDEX = 3;
    public static final int LANGUAGE_INDEX = 4;
    public static final int TURTLES_INDEX = 5;
    public static final int ACTIVES_INDEX = 6;

    private static final String TAB_NAME = "SLogo #";
    private static final String DEFAULT_PACKAGE = "resources/languages/English";
    private static final String DEFAULT_BACKGROUND_COLOR = "-fx-background-color: white";
    private static final String DEFAULT_PEN_COLOR = "-fx-background-color: black";
    private static final double DEFAULT_PEN_SIZE = 3;
    private static final Image DEFAULT_IMAGE = new Image("resources/images/default.jpg");

    private TabPane myTabs;
    private BorderPane myView;
    private Scene myScene;
    private TopMenu myMenu;
    private EventHandler<ActionEvent> createNewTab;
    private List<ObjectProperty> myBindings;
    private ObjectProperty<String> canvasColor, penColor;
    private ObjectProperty<Double> penSize;
    private ObjectProperty<Image> turtleImage;
    private ObjectProperty<ResourceBundle> language;
    private ObjectProperty<ArrayList<Turtle>> turtles;
    private ObjectProperty<ArrayList<Turtle>> actives;
    
    public UIManager () {
        initialize();
    }

    /**
     * initializes the necessary components to create a TabPane with at least one instance of UI
     */
    private void initialize () {
        myView = new BorderPane();
        myTabs = new TabPane();
        setUpEventHandlers();
        setUpBindings();
        myMenu = new TopMenu(createNewTab, myBindings);
        myView.setCenter(myTabs);
        myView.setTop(myMenu.getMenuBar());
        addTab();
        myScene = new Scene(myView, UI_WIDTH, UI_HEIGHT);
    }

    /**
     * sets up the binding properties for canvas color, pen color, pen size, turtle image, and language
     */
    private void setUpBindings () {
        canvasColor = new SimpleObjectProperty<String>(DEFAULT_BACKGROUND_COLOR);
        penColor = new SimpleObjectProperty<String>(DEFAULT_PEN_COLOR);
        penSize = new SimpleObjectProperty<Double>(DEFAULT_PEN_SIZE);
        turtleImage = new SimpleObjectProperty<Image>(DEFAULT_IMAGE);
        language = new SimpleObjectProperty<ResourceBundle>(
                                                            ResourceBundle
                                                                    .getBundle(DEFAULT_PACKAGE));
        turtles = new SimpleObjectProperty<ArrayList<Turtle>>();
        actives = new SimpleObjectProperty<ArrayList<Turtle>>();
        myBindings = new ArrayList<ObjectProperty>(Arrays.asList(canvasColor, penColor, penSize,
                                                                 turtleImage, language,
                                                                 turtles, actives));
    }

    /**
     * returns the scene to be viewed by main
     * @return Scene myScene
     */
    public Scene getScene () {
        return myScene;
    }

    /**
     * sets up all EventHandlers that can be passed into subclasses of the UI
     */
    private void setUpEventHandlers () {
        createNewTab = e -> addTab();
    }

    /**
     * method called when user creates new tab on the GUI
     */
    private void addTab () {
        UI ui = new UI(myBindings);
        Tab tab = new Tab(TAB_NAME + (myTabs.getTabs().size() + 1));
        tab.setContent(ui.getUI());
        myTabs.getTabs().add(tab);
    }

    
}
