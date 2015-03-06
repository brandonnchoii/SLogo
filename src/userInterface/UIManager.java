package userInterface;

import java.util.ResourceBundle;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class UIManager {

    public static final int UI_WIDTH = 1000;
    public static final int UI_HEIGHT = 750;
    
    private static final String TAB_NAME = "SLogo #";
    private static final String RESOURCE_PACKAGE = "resources/languages/";
    private static final String DEFAULT_PACKAGE = "resources/languages/English";
    
    private ResourceBundle language;
    private TabPane myTabs;
    private BorderPane myView;
    private Scene myScene;
    private TopMenu myMenu;
    private EventHandler<ActionEvent> createNewTab;
    
    public UIManager () {
        initialize();
    }

    private void initialize () {
        language = ResourceBundle.getBundle(DEFAULT_PACKAGE);
        myView = new BorderPane();
        myTabs = new TabPane();
        setUpEventHandlers();
        myMenu = new TopMenu(createNewTab, language);
        myView.setCenter(myTabs);
        myView.setTop(myMenu.getMenuBar());
        addTab();
        myScene = new Scene(myView, UI_WIDTH, UI_HEIGHT);
    }
    
    public Scene getScene() {
        return myScene;
    }
    
    /**
     * sets up all EventHandlers that can be passed into subclasses of the UI 
     */
    private void setUpEventHandlers() {
        createNewTab = e -> addTab();
    }
    
    /**
     * method called when user creates new tab on the GUI
     */
    private void addTab() {
        UI ui = new UI(language);
        Tab tab = new Tab(TAB_NAME + (myTabs.getTabs().size() + 1));
        tab.setContent(ui.getUI());
        myTabs.getTabs().add(tab);
    }
}
