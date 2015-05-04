// This entire file is part of my masterpiece.
// Brandon Choi

package imagePicker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.beans.property.ObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * ImagePickerUnit represents one image node in the ImagePicker. Upon double click, a file chooser
 * pops up and allows the user to change the image of the turtle selected. The image properties on
 * the ImagePickerUnit and the Turtle are binded together so that changing one changes the other as
 * well. This was part of my extension for VOOGASalad.
 * 
 * @author Brandon Choi
 *
 */

public class ImagePickerUnit {

    private static final String IMAGE_PATH = "resources/images/";
    private static final int GRAPHIC_SIZE = 50;
    private static final String FILE_CHOOSER_PROMPT_TEXT = "Pick a new Turtle image!";

    private HBox container;
    private ImageView graphic;
    private ObjectProperty<Image> myProperty;

    public ImagePickerUnit (ObjectProperty<Image> property) {
        container = new HBox();
        graphic = new ImageView(property.get());
        graphic.setFitWidth(GRAPHIC_SIZE);
        graphic.setFitHeight(GRAPHIC_SIZE);
        myProperty = property;
        graphic.imageProperty().bind(myProperty);
        container.getChildren().add(graphic);
        container.setAlignment(Pos.CENTER);
        setUpClick();
    }

    public ImageView getImageView () {
        return graphic;
    }

    /**
     * upon double click on the image, a file chooser opens up that allows the user to change the
     * image
     */
    private void setUpClick () {
        graphic.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                openFileChooser();
            }
        });
    }

    /**
     * opens the file chooser and lets the user select a new image for the graphic
     */
    private void openFileChooser () {
        Stage s = new Stage();
        FileChooser fc = new FileChooser();
        fc.setTitle(FILE_CHOOSER_PROMPT_TEXT);
        fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg",
                                                            "*.gif"));
        File chosen = fc.showOpenDialog(s);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(chosen);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Image i = SwingFXUtils.toFXImage(bi, null);
        myProperty.setValue(i);
    }
}
