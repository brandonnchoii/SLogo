package pen;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class DottedPen extends Pen{

	private static final double DEFAULT_INTERVAL = 10.0;
	
	public DottedPen(Paint color) {
		super(color, DEFAULT_INTERVAL);		
	}

	public DottedPen(Paint color, double interval) {
		super(color, interval);
	}
	
	@Override
	public void drawLine(GraphicsContext gc, Point2D goal, Point2D current, Point2D shift) {
        double currX = current.getX();
        double currY = current.getY();
        double xSlope = (goal.getX() - current.getX())/myInterval;
        double ySlope = (goal.getY() - current.getY())/myInterval;
        gc.setFill(myPenColor);
        gc.setLineWidth(mySize);      	
        if (isDrawing) {
        	for (int i = 0; i < myInterval; i++) {
        		if (isDrawing) {
        		gc.strokeLine(currX + shift.getX(), currY + shift.getY(), 
        				currX + shift.getX(), currY + shift.getY());
        		currX += xSlope;
        		currY += ySlope;
        		isDrawing = !isDrawing;     	
        		}
        	}
        }
	}
	
}
