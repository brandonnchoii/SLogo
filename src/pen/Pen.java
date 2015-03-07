package pen;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
//delete class or move it? lots of getters/setters, pointless?
public class Pen {
	private static final double DEFAULT_PEN_SIZE = 1;	
	
	protected Paint myPenColor;
	protected double mySize;
	protected boolean isDrawing;
	protected double myInterval;
	
	public Pen(Paint color) {
		isDrawing = true;
		myPenColor = color;
		mySize = DEFAULT_PEN_SIZE;
	}

	public Pen(Paint color, double interval) {
		isDrawing = true;
		myPenColor = color;
		mySize = DEFAULT_PEN_SIZE;
		myInterval = interval;
	}
	
	public void drawLine(GraphicsContext gc, Point2D goal, Point2D current, Point2D shift) {
        if (isDrawing) {
        	double shiftX = shift.getX();
        	double shiftY = shift.getY();
        	gc.setFill(myPenColor);
        	gc.setLineWidth(mySize);      	
        	gc.strokeLine(current.getX() + shiftX, current.getY() + shiftY, 
        			goal.getX() + shiftX, goal.getY() + shiftY);
	
        }
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
