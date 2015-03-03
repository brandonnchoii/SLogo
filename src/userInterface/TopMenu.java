package userInterface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class TopMenu {

    private MenuBar myMenu;
    private Menu File, Edit, View, Preferences, Help;
    protected ColorPicker myColorChoices;
    private EventHandler<ActionEvent> createNewTab;

    public TopMenu (EventHandler<ActionEvent> tabHandler) {
        createNewTab = tabHandler;
        initialize();
    }

    public MenuBar getMenuBar () {
        return myMenu;
    }

    private void initialize () {
        myMenu = new MenuBar();
        myColorChoices = new ColorPicker();
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

    private void displayWebpage () {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(getClass().getResource("/html/helpPage.html").toExternalForm());
        display(browser);
    }

    private void displayColorPicker () {
        StackPane colorPick = new StackPane();
        VBox content = new VBox(10);
        Label text = new Label("Background Color:");
        content.getChildren().addAll(text, myColorChoices);
        colorPick.getChildren().add(content);

        myColorChoices.setOnAction(e -> {

        });

        display(colorPick);
    }

    private void displayPenEditor () {

    }

    private void display (Node n) {
        Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(n);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

}
