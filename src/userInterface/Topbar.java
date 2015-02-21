package userInterface;

import javafx.application.*;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

public class Topbar {

    private static final String [] COLOR_NAMES = {"RED", "BLUE", "GREEN", "PURPLE", "YELLOW", "ORANGE"};
    private static final Color[] COLORS = { Color.RED, Color.BLUE, Color.GREEN, Color.PURPLE,
                                           Color.YELLOW, Color.ORANGE };
    private static final String[] LANGUAGES = { "Chinese", "English", "French", "German",
                                               "Italian", "Japanese", "Korean", "Portuguese",
                                               "Russian", "Spanish" };
    private static final int SPACING = 50;

    private HBox myHBox;
    private Map<String, Color> colorMap;

    public Topbar () {
        myHBox = new HBox(SPACING);
        initialize();
    }

    public HBox getTopbar () {
        return myHBox;
    }

    private void initialize () {
        myHBox.getChildren()
                .addAll(createHelpLink(), createColorOptions(), createLanguageOptions());
    }

    private Hyperlink createHelpLink () {
        Hyperlink link  = new Hyperlink("HELP?");
        
        
        return link;
    }

    private ComboBox<String> createColorOptions () {
        ComboBox<String> colorChoices = new ComboBox<>();
        colorChoices.setPromptText("Set Background Color:");
        
        colorMap = new HashMap<String, Color>();
        for (int i = 0; i < COLOR_NAMES.length; i++)
            colorMap.put(COLOR_NAMES[i], COLORS[i]);
        
        for (String s: colorMap.keySet())
            colorChoices.getItems().add(s);
        
        return colorChoices;
    }

    private ComboBox<String> createLanguageOptions () {
        ComboBox<String> langaugeChoices = new ComboBox<String>();
        langaugeChoices.setPromptText("Set Language:");
        for (String s: LANGUAGES)
            langaugeChoices.getItems().add(s);
        return langaugeChoices;
    }
}
