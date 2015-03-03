package userInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class TopMenu {

    private static final int PANE_SPACING = 5;
    private static final int POPUP_WIDTH = 250;
    private static final int POPUP_HEIGHT = 150;
    
    private MenuBar myMenu;
    private Menu File, Edit, View, Preferences, Help;
    private ColorPicker myBackgroundColorChoices;
    private ColorPicker myPenColorChoices;
    private EventHandler<ActionEvent> createNewTab;
    private Stage myStage;

    public TopMenu (EventHandler<ActionEvent> tabHandler) {
        createNewTab = tabHandler;
        initialize();
    }

    public MenuBar getMenuBar () {
        return myMenu;
    }

    private void initialize () {
        myMenu = new MenuBar();
        myBackgroundColorChoices = new ColorPicker();
        myPenColorChoices = new ColorPicker();
        myMenu.getMenus().addAll(createFileMenu(), createEditMenu(), createViewMenu(),
                                 createPreferencesMenu(), createHelpMenu());
    }

    // use reflection to create all of them, save code
    private Menu createMenu (String s) {
        Menu m = new Menu();
        MenuItem m1, m2, m3;
        return m;
    }

    private Menu createFileMenu () {
        File = new Menu("File");
        MenuItem m1 = new MenuItem("New SLogo Tab");
        m1.setOnAction(createNewTab);
        MenuItem m2 = new MenuItem("Exit");
        m2.setOnAction(e -> System.exit(1));

        File.getItems().addAll(m1, m2);
        return File;
    }

    private Menu createEditMenu () {
        Edit = new Menu("Edit");
        MenuItem m1 = new MenuItem("");
        MenuItem m2 = new MenuItem("");
        MenuItem m3 = new MenuItem("");

        Edit.getItems().addAll(m1, m2, m3);
        return Edit;
    }

    private Menu createViewMenu () {
        View = new Menu("View");
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
        Help = new Menu("Help");
        MenuItem m1 = new MenuItem("What is SLogo?");
        m1.setOnAction(e -> displayWebpage());
        Help.getItems().add(m1);
        return Help;
    }

    private Menu createPreferencesMenu () {
        Preferences = new Menu("Preferences");
        MenuItem m1 = new MenuItem("Change Language");
        // m1.setOnAction(e -> );
        Preferences.getItems().add(m1);
        return Preferences;
    }

    /**
     * displays the HTML web page with instructions on SLogo and additional information about the software
     */
    private void displayWebpage () {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(getClass().getResource("/html/helpPage.html").toExternalForm());
        display(browser);
    }

    /**
     * displays the background color editor
     */
    private void displayColorPicker () {
        myBackgroundColorChoices.setOnAction(e -> {
            System.out.println(myBackgroundColorChoices.getValue().toString());
        });
        display(createDisplayBox(Arrays.asList(new Label("Set Background Color: "), myBackgroundColorChoices)));
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
        display(createDisplayBox(Arrays.asList(sliderBox, colorBox)));
    }
    
    private void displayImagePicker () {
        myStage = new Stage();
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select image");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Images Files", "*.png", "*.jpg", ".gif"));
        File selectedFile = chooser.showOpenDialog(myStage);
            //System.out.println(selectedFile.toString());
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
        myStage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(n);
        myStage.setScene(scene);
        myStage.show();
        myStage.centerOnScreen();
    }
}
