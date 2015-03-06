package turtle;


import java.text.DecimalFormat;
import java.text.NumberFormat;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import command.Command;

public class Turtle extends ImageView {

	private static final double DEFAULT_POS = 0;
	private static final double DEFAULT_SPEED = 10.0;
	private static final boolean DEFAULT_DRAW = true;
	private static final boolean DEFAULT_VISIBLE = true;
	private static final Image DEFAULT_IMAGE = new Image("resources/images/default.jpg");
	
	private double direction;
	private boolean active;
	private int id;
	private double mySpeed;
	private Pen myPen;
	private Point2D current;
	private Point2D next;
	private Point2D goal;

	public Turtle(Paint color, int ID) {
		myPen = new Pen(color);
		initializeTurtleDefaults();
		active = true;
		id = ID;
	}


	public Turtle(int x, int y, double dir, Image i, Paint color, int ID){
		myPen = new Pen(color);
		initializeTurtleDefaults();
		active = true;
		id = ID;
	}

    public Turtle(Image i, Paint color, int ID){
        myPen = new Pen(color);
		initializeTurtleDefaults();
        active = true;
        id = ID;
    }

    public Turtle(int x, int y, Image i, Paint color, int ID){
        myPen = new Pen(color);
		initializeTurtleDefaults();
        active = true;
        id = ID;
    }
	
	private void initializeTurtleDefaults() {
		mySpeed = DEFAULT_SPEED;
		current = new Point2D(DEFAULT_POS, DEFAULT_POS);
		next = new Point2D(DEFAULT_POS, DEFAULT_POS);
		goal = new Point2D(DEFAULT_POS, DEFAULT_POS);
		setTranslateX(DEFAULT_POS);
		setTranslateY(DEFAULT_POS);
		setFitWidth(70);
		setFitHeight(70);
		direction = DEFAULT_POS;
		myPen.changePenState(DEFAULT_DRAW);
		setVisible(DEFAULT_VISIBLE);
		setImage(DEFAULT_IMAGE);
	}

	
	public boolean isDrawing() {
		return myPen.penReady();
	}

	public void setDrawing(boolean b){
		myPen.changePenState(b);
	}

	public String act(Command c) {
		return c.run(this) + "";
	}

	public void move (double pixels) {
		goal = new Point2D(getTranslateX() + pixels*Math.cos(radians()),
				getTranslateY() + pixels*Math.sin(radians()));
	}

	private double radians(){
		return Math.toRadians(direction) - Math.PI/2;
	}

	public void fixPos(double height, double width){
		setTranslateX(getTranslateX() % width);
		setTranslateY(getTranslateY() % height);
	}

	public void rotate (double degrees) {
		direction += degrees;
		setRotate(direction);
	}

	public void setHeading (double degrees) {
		direction = degrees % 360;
		if (direction < 0)
			direction = 360 + direction;
		setRotate(direction);
	}

	public double getDirection() {
		return direction;
	}

	public void moveTo(double x, double y){
		setTranslateX(x);
		setTranslateY(y);
		goal = new Point2D(x, y);
	}
	
	public void animatedMove(GraphicsContext gc, Point2D shift) {
		double currX = current.getX();
		double currY = current.getY();
        if (current.distance(goal) < mySpeed) {
        	moveMyself(goal.getX(), goal.getY());
        	myPen.drawLine(gc, goal, current, shift);
        	current = goal;
        	return;
        }
    	Point2D nextPoint = findNextPoint(currX, currY, 
        		goal.getX(), goal.getY());
    	moveMyself(nextPoint.getX(), nextPoint.getY());
    	myPen.drawLine(gc, nextPoint, current, shift);
		current = nextPoint;
	}
	
	private void moveMyself(double x, double y) {
		setTranslateX(x);
		setTranslateY(y);
	}
	
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
	
	public Point2D nextFromCurrent() {
		return findNextPoint(current.getX(), current.getY(),
				goal.getX(), goal.getY());
	}
	
	public void updatePenAttributes(GraphicsContext gc) {
		gc.setStroke(myPen.getColor());
        gc.setLineWidth(myPen.getSize());
	}
	
	private boolean atGoal() {
		return current == goal;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public int getID(){
		return id;
	}
	
    public String truncate (double d){
        NumberFormat nf = new DecimalFormat("#.##");
        return nf.format(d);
    }
    
    public void setActive(boolean b) {
        active = b;
    }
    
    //old animation-less drawing (use if animation fails)
    //call still exists in controller - just uncomment
    public void previousDrawLine(GraphicsContext gc, double shiftX, double shiftY) {
        double currX = current.getX();
        double currY = current.getY();
        double nextX = getTranslateX();
        double nextY = getTranslateY();
        gc.setStroke(myPen.getColor());
        gc.setLineWidth(myPen.getSize());
        setTranslateX(nextX);
        setTranslateY(nextY);
        if (myPen.penReady()) {
            gc.strokeLine(currX + shiftX, currY + shiftY, 
                          next.getX() + shiftX, next.getY() + shiftY);
        }
        current = new Point2D(nextX, nextY);
    }

}

