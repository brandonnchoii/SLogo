package userInterface;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class TopMenu {

    private static final String HEX_PREFIX = "x";
    private static final int COLOR_SUBSTRING_INDEX = 1;
    private static final String COLOR_PATH = "-fx-background-color: #";
    private static final String IMAGE_PATH = "resources/images/";
    private static final String RESOURCE_PATH = "resources/languages/";
    private static final String HTML_HELP_PAGE = "/html/helpPage.html";
    private static final int PANE_SPACING = 5;
    private static final int POPUP_WIDTH = 250;
    private static final int POPUP_HEIGHT = 150;
    private static final String[] LANGUAGES = { "Chinese", "English", "French", "German",
                                               "Italian", "Japanese", "Korean", "Portuguese",
                                               "Russian", "Spanish" };

    private MenuBar myMenu;
    private Menu File, Edit, View, Preferences, Help;
    private ColorPicker myBackgroundColorChoices;
    private ColorPicker myPenColorChoices;
    private EventHandler<ActionEvent> createNewTab;
    private Stage myStage;
    private ObjectProperty<String> canvasColor, penColor;
    private ObjectProperty<Double> penSize;
    private ObjectProperty<Image> turtleImage;
    private ObjectProperty<ResourceBundle> language;

    public TopMenu (EventHandler<ActionEvent> tabHandler, List<ObjectProperty> bindings) {
        initialize(tabHandler, bindings);
    }

    public MenuBar getMenuBar () {
        return myMenu;
    }

    private void initialize (EventHandler<ActionEvent> tabHandler, List<ObjectProperty> bindings) {
        createNewTab = tabHandler;
        canvasColor = bindings.get(UIManager.CANVAS_INDEX);
        penColor = bindings.get(UIManager.PEN_COLOR_INDEX);
        penSize = bindings.get(UIManager.PEN_SIZE_INDEX);
        turtleImage = bindings.get(UIManager.TURTLE_IMAGE_INDEX);
        language = bindings.get(UIManager.LANGUAGE_INDEX);

        myMenu = new MenuBar();
        myBackgroundColorChoices = new ColorPicker();
        myPenColorChoices = new ColorPicker();
        myStage = new Stage();
        myMenu.getMenus().addAll(createFileMenu(), createEditMenu(), createViewMenu(),
                                 createPreferencesMenu(), createHelpMenu());
    }

    // use reflection to create all of them, save code
    // private Menu createMenu (String s) {
    // Menu m = new Menu();
    // MenuItem m1, m2, m3;
    // return m;
    // }

    private Menu createFileMenu () {
        File = new Menu(language.getValue().getString("File"));
        MenuItem m1 = new MenuItem("New SLogo Tab");
        m1.setOnAction(createNewTab);
        MenuItem m2 = new MenuItem("Exit");
        m2.setOnAction(e -> System.exit(1));
        File.getItems().addAll(m1, m2);
        return File;
    }

    private Menu createEditMenu () {
        Edit = new Menu("Edit");
        MenuItem m1 = new MenuItem("Add Turtle");
        Edit.getItems().addAll(m1);
        return Edit;
    }

    private Menu createViewMenu () {
        View = new Menu(language.getValue().getString("View"));
        MenuItem m1 = new MenuItem("Set Background Color");
        m1.setOnAction(e -> displayColorPicker());
        MenuItem m2 = new MenuItem("Set Pen Settings");
        m2.setOnAction(e -> displayPenEditor());
        MenuItem m3 = new MenuItem("Set Current Turtle's Image");
        m3.setOnAction(e -> displayImagePicker());

        View.getItems().addAll(m1, m2, m3);
        return View;
    }

    private Menu createHelpMenu () {
        Help = new Menu(language.getValue().getString("Help"));
        MenuItem m1 = new MenuItem("What is SLogo?");
        m1.setOnAction(e -> displayWebpage());
        Help.getItems().add(m1);
        return Help;
    }

    private Menu createPreferencesMenu () {
        Preferences = new Menu(language.getValue().getString("Preferences"));
        MenuItem m1 = new MenuItem("Change Language");
        m1.setOnAction(e -> displayLanguageSelector());
        Preferences.getItems().add(m1);
        return Preferences;
    }

    /**
     * displays the HTML web page with instructions on SLogo and additional information about the
     * software
     */
    private void displayWebpage () {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(getClass().getResource(HTML_HELP_PAGE).toExternalForm());
        display(browser);
    }

    /**
     * displays the background color editor
     */
    private void displayColorPicker () {
        myBackgroundColorChoices.setOnAction(e -> {
            canvasColor.setValue(COLOR_PATH + extractColor(myBackgroundColorChoices.getValue()));
        });
        display(createDisplayBox(Arrays.asList(new Label("Set Background Color: "),
                                               myBackgroundColorChoices)));
    }

    /**
     * extract the hex values of the Color's string by substringing everything after the 0x;
     * @param c
     * @return
     */
    private String extractColor (Color c) {
        return c.toString().substring(c.toString().indexOf(HEX_PREFIX) + COLOR_SUBSTRING_INDEX);
    }

    /**
     * displays the pen editor that allows user to edit size, color, and style
     */
    private void displayPenEditor () {
        HBox sliderBox = new HBox(PANE_SPACING);
        Slider sizeSlider = new Slider(0, 25, 1);
        sizeSlider.setShowTickLabels(true);
        sizeSlider.setShowTickMarks(true);
        sliderBox.getChildren().addAll(new Label("Set Pen Size: "), sizeSlider);
        HBox colorBox = new HBox(PANE_SPACING);
        colorBox.getChildren().addAll(new Label("Set Pen Color: "), myPenColorChoices);
        sizeSlider.setOnMouseClicked(e -> {
            penSize.setValue(sizeSlider.getValue());
        });

        myPenColorChoices.setOnAction(e -> {
            penColor.setValue(extractColor(myPenColorChoices.getValue()));
        });
        display(createDisplayBox(Arrays.asList(sliderBox, colorBox)));
    }

    /**
     * displays a file chooser that allows user to select desired image for turtle
     */
    private void displayImagePicker () {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select image");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Images Files", "*.png", "*.jpg",
                                                                 ".gif"));
        File selectedFile = chooser.showOpenDialog(myStage);
        turtleImage.setValue(new Image(IMAGE_PATH + selectedFile.getName()));
    }

    /**
     * displays the language selector for the user to pick which language will be read in by the
     * program
     */
    private void displayLanguageSelector () {
        ComboBox<String> languageChoices = new ComboBox<>();
        languageChoices.setPromptText("Language:");
        Arrays.stream(LANGUAGES).forEach(e -> languageChoices.getItems().add(e));
        languageChoices.setOnAction(e -> {
            language.setValue(ResourceBundle.getBundle(RESOURCE_PATH +
                                                       languageChoices.getSelectionModel()
                                                               .getSelectedItem()));
        });
        display(createDisplayBox(Arrays.asList(languageChoices)));
    }

    /**
     * returns the appropriate display box with all desired nodes added on
     */
    private Pane createDisplayBox (List<Node> nodes) {
        VBox vb = new VBox(PANE_SPACING);
        nodes.stream().forEach(e -> vb.getChildren().add(e));
        vb.setAlignment(Pos.CENTER);
        vb.setMinSize(POPUP_WIDTH, POPUP_HEIGHT);
        return vb;
    }

    /**
     * displays any node passed in on a new, centered stage
     */
    private void display (Node n) {
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(n);
        myStage.setScene(scene);
        myStage.show();
        myStage.centerOnScreen();
    }
}
