package pen;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class DashedPen extends Pen{

	private static final double DEFAULT_INTERVAL = 10.0;
	
	public DashedPen(Paint color) {
		super(color, DEFAULT_INTERVAL);		
	}

	public DashedPen(Paint color, double interval) {
		super(color, interval);
	}
	
	@Override
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
	
}
