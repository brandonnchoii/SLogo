/**
 *
 * @author Brandon Choi
 *
 */

package userInterface;

import javafx.geometry.Insets;

import java.util.Arrays;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Topbar {

    private static final String[] LANGUAGES = { "Chinese", "English", "French", "German",
                                               "Italian", "Japanese", "Korean", "Portuguese",
                                               "Russian", "Spanish" };
    private static final int SPACING = 40;

    private HBox myHBox;
    private ComboBox<String> languageChoices;
    private Hyperlink myLink;
    private ColorPicker myColorChoices;

    public Topbar () {
        initialize();
    }

    public HBox getTopbar () {
        return myHBox;
    }

    private void initialize () {
        myHBox = new HBox(SPACING);
        myHBox.setPadding(new Insets(15, 15, 15, 15));
        myHBox.getChildren()
                .addAll(createHelpLink(), createColorChoices(), createLanguageOptions());
    }

    private Hyperlink createHelpLink () {
        myLink = new Hyperlink("HELP?");
        myLink.setOnMouseClicked(e -> displayWebpage());
        return myLink;
    }

    private void displayWebpage () {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(getClass().getResource("/html/helpPage.html").toExternalForm());

        Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root);

        root.getChildren().add(browser);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }

    private HBox createColorChoices () {
        HBox hb = new HBox(10);
        Label label = new Label("Background:");
        myColorChoices = new ColorPicker();
        hb.getChildren().addAll(label, myColorChoices);
        return hb;
    }

    private ComboBox<String> createLanguageOptions () {
        languageChoices = new ComboBox<String>();
        languageChoices.setPromptText("Language:");
        Arrays.stream(LANGUAGES).forEach(e -> languageChoices.getItems().add(e));
        return languageChoices;
    }
}
