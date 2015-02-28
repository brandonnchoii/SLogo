package turtle;

import javafx.scene.paint.Paint;
//delete class or move it? lots of getters/setters, pointless?
public class Pen {
	private static final double DEFAULT_PEN_SIZE = 2;
	private static final double DEFAULT_START = 0.0;
	
	
	private Paint myPenColor;
	private double mySize;
	private boolean isDrawing;
	
	public Pen(Paint color) {
		isDrawing = true;
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
	
	public boolean penReady() {
		return isDrawing;
	}
	
	public void changePenState(boolean b) {
		isDrawing = b;
	}

}
