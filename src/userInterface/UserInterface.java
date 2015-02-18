package userInterface;

import worldController.WorldController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserInterface {
    
    //private static UserInterface instance;
    private static final int myWidth = 500;
    private static final int myHeight = 500;    
    
    private HBox commandField;
    private VBox mySidebar;
    private WorldController myController;
    private Scene myScene;
    private Group myRoot;
    
    public UserInterface() {
    	myRoot = new Group();
    	myScene = new Scene(myRoot);
    }
    
    /**
     * Implement Singleton OO Design architecture
     * do we need to this even if we are making a single case of it from main only anyway?
     */
    /*public static synchronized UserInterface getInstance() {
        if (instance == null)
            instance = new UserInterface(myWidth, myHeight);
        
        return instance;
    }*/
    
    public void refresh(String s) {
        
    }
    
}
