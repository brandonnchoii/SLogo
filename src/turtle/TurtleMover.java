// This entire file is part of my masterpiece.
// JAMES MOSCA

package turtle;

import pen.Pen;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class TurtleMover {

	private double mySpeed;
	private Point2D current;
	private Point2D goal;
    private PointQueue myGoalQ;
    private double direction;
    
    public TurtleMover(double speed) {
        current = new Point2D(0, 0);
        goal = new Point2D(0, 0);
        myGoalQ = new PointQueue();
        direction = 0;
        mySpeed = speed;
    }
    
	public void move(double pixels, double dir) {
		direction = dir;
		if (myGoalQ.size() != 0) {
			Point2D next = new Point2D(myGoalQ.peakLast().getX() + pixels*Math.cos(radians()),
					myGoalQ.peakLast().getY() + pixels*Math.sin(radians()));
			myGoalQ.enqueue(next);
		}
		else {
			Point2D next = new Point2D(current.getX() + pixels*Math.cos(radians()),
				current.getY() + pixels*Math.sin(radians()));
			myGoalQ.enqueue(next);
		}
	}
    
	public void newGoal(Turtle t, double x, double y) {
		t.setTranslateX(x);
		t.setTranslateY(y);
		goal = new Point2D(x, y);
	}
	
    /**
     * Checks values of a given queue by comparing them to the current
     * position and goal of the turtle
     * @param q
     */
    public void checkQueue(PointQueue q) {
    	if (q.size() == 0) {
    		goal = current;
    		return;
    	}
    	if (current != q.peak()) {
    		goal = q.peak();
    		return;
    	}
    	if (current == q.peak()) {
    		q.removeFirst();  
    		checkQueue(q);
    	}
    }
	
	/**
	 * Moves the turtle incrementally towards its goal
	 * Draws a line to the turtle's current position
	 * @param gc
	 * @param shift
	 */
	public void animation(Turtle t, Pen p, GraphicsContext gc, Point2D shift) {
		double currX = current.getX();
		double currY = current.getY();
		checkQueue(myGoalQ);
		if (current.distance(goal) < mySpeed) {
			newGoal(t, goal.getX(), goal.getY());
			p.drawLine(gc, goal, current, shift);	
        	current = goal;
        	return;
        }
		Point2D nextPoint = findNextPoint(currX, currY, 
        		goal.getX(), goal.getY());
    	newGoal(t, nextPoint.getX(), nextPoint.getY());
    	p.drawLine(gc, nextPoint, current, shift);
    	gc.strokeLine(currX, currY, nextPoint.getX(), nextPoint.getY());
    	current = nextPoint;
	}
	
	/**
	 * Given a speed of the turtle, the turtle moves its speed towards 
	 * the goal coordinate
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 * @return
	 */
	private Point2D findNextPoint(double x0, double y0, double x1, double y1) {
		if (atGoal()) {
			return goal;
		}
		Point2D v = new Point2D(x1 - x0, y1 - y0);
		double vMag = Math.sqrt(Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2));
		Point2D u = new Point2D(v.getX()/vMag, v.getY()/vMag);
		Point2D next = new Point2D(x0 + mySpeed*(u.getX()), 
				y0 + mySpeed*(u.getY()));
		return next;
	}
	
	/**
	 * Finds the next point from standard values of the current 
	 * turtle position, using its current goal
	 * @return
	 */
	public Point2D nextFromCurrent() {
		return findNextPoint(current.getX(), current.getY(),
				goal.getX(), goal.getY());
	}
	
	private boolean atGoal() {
		return current == goal;
	}
	
	private double radians(){
		return Math.toRadians(direction) - Math.PI/2;
	}
	
	public void changeDirection (Turtle t, double degrees) {
		direction += degrees;
		t.setRotate(direction);
	}
	
}
