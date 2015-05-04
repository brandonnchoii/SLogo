// This entire file is part of my masterpiece.
// Brandon Choi

package imagePicker;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The view for all Turtle images. All the images are composed from ImagePickerUnits. This is part
 * of the extension of SLogo from VOOGASalad.
 * 
 * @author Brandon Choi
 *
 */

public class ImagePicker {

    private static final int PREF_SIZE = 400;
    private static final int PREF_SPACING = 15;
    private Stage myStage;
    private Scene myScene;
    private VBox container;
    private List<ImagePickerUnit> choices;

    public ImagePicker () {
        myStage = new Stage();
        container = new VBox(PREF_SPACING);
        container.setAlignment(Pos.CENTER);
        container.setPrefSize(400, PREF_SIZE);
        myScene = new Scene(container);
        myStage.setScene(myScene);
        choices = new ArrayList<>();
    }

    /**
     * Displays the picker on the center of the screen in a new pop-up stage
     */
    public void displayPicker () {
        myStage.show();
        myStage.centerOnScreen();
    }

    /**
     * Adds an ImagePickerUnit to the Picker
     */
    public void addUnit (ImagePickerUnit i) {
        choices.add(i);
        container.getChildren().add(i.getImageView());
    }
}
