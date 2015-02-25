package turtle;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Pen {
	private static final double DEFAULT_PEN_SIZE = 1.0;
	
	private Paint myPenColor;
	private double mySize;
	
	public Pen(Paint color) {
		myPenColor = color;
		mySize = DEFAULT_PEN_SIZE;
	}

	public void changePenColor(Paint color) {
		myPenColor = color;
	}
	
	public void changePenSize(double size) {
		mySize = size;
	}
	
	public Paint getColor() {
		return myPenColor;
	}

	public double getSize() {
		return mySize;
	}
	
}
