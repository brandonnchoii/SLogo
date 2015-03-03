package userInterface;

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
    
    private TabPane myTabs;
    private BorderPane myView;
    private Scene myScene;
    private TopMenu myMenu;

    private EventHandler<ActionEvent> createNewTab, changeLanguage;
    
    public UIManager () {
        initialize();
    }

    private void initialize () {
        myView = new BorderPane();
        myTabs = new TabPane();
        setUpEventHandlers();
        myMenu = new TopMenu(createNewTab);
        myView.setCenter(myTabs);
        myView.setTop(myMenu.getMenuBar());
        addTab();
        myScene = new Scene(myView, UI_WIDTH, UI_HEIGHT);
    }
    
    public Scene getScene() {
        return myScene;
    }
    
    private void setUpEventHandlers() {
        createNewTab = e -> addTab();
    }
    
    private void addTab() {
        UI ui = new UI();
        Tab tab = new Tab(TAB_NAME + (myTabs.getTabs().size() + 1));
        tab.setContent(ui.getUI());
        myTabs.getTabs().add(tab);
    }
}
