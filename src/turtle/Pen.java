package turtle;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Pen {
	private static final double DEFAULT_PEN_SIZE = 1.0;
	private static final double DEFAULT_START = 0.0;
	
	
	private Paint myPenColor;
	private double mySize;
	private boolean isDrawing;
	private Point2D current;
	private Point2D next;
	
	public Pen(Paint color) {
		isDrawing = true;
		myPenColor = color;
		mySize = DEFAULT_PEN_SIZE;
		current = new Point2D(DEFAULT_START, DEFAULT_START);
		next = new Point2D(DEFAULT_START, DEFAULT_START);
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
	
	public void setCurrent(Point2D pt) {
		current = pt;
	}
	
	public void setNext(Point2D pt) {
		next = pt;
	}
	
}
