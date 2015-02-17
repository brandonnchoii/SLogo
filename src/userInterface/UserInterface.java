package userInterface;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserInterface {
    
    private static UserInterface instance;
    
    private HBox commandField;
    private VBox mySidebar;
    private WorldController myController;
    
    public UserInterface() {
        
    }
    
    /**
     * Implement Singleton OO Design architecture
     * do we need to this even if we are making a single case of it from main only anyway?
     */
    public static synchronized UserInterface getInstance() {
        if (instance == null)
            instance = new UserInterface();
        
        return instance;
    }
    
    public void refresh(String s) {
        
    }
    
}
