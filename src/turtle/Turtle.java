// This entire file is part of my masterpiece.
// JAMES MOSCA

package turtle;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Queue;

import pen.DefaultPen;
import pen.DottedPen;
import pen.Pen;
import worldController.WorldController;
import userInterface.UIManager;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import command.Command;

public class Turtle extends ImageView {

    private static final int DEFAULT_SIZE = 25;
	private static final double DEFAULT_POS = 0;
	private static final double DEFAULT_SPEED = 10.0;
	private static final boolean DEFAULT_DRAW = true;
	private static final boolean DEFAULT_VISIBLE = true;
	private static final Image DEFAULT_IMAGE = new Image("resources/images/default.jpg");
	
	private double direction;
	private boolean active;
	private int id;
	//private double mySpeed;
	private Pen myPen;
	//private Point2D current;
	//private Point2D goal;
    private ObjectProperty<Image> myImage;
    //private PointQueue myGoalQ;
    private TurtleMover myMover;
    
	public Turtle(Paint color, int ID) {
		myPen = new DefaultPen(color);
		initializeTurtleDefaults();
		active = true;
		id = ID;
	}

    public Turtle(Image i, Paint color, int ID){
        myPen = new DefaultPen(color);
		initializeTurtleDefaults();
    }
    
    public Turtle(List<ObjectProperty> bindings, Paint color, int ID) {
        myPen = new Pen(color);
        initializeTurtleDefaults();
        setUpBindings(bindings);
        active = true;
        id = ID;
    }

    public Turtle(int x, int y, Image i, Paint color, int ID){
        myPen = new DefaultPen(color);
		initializeTurtleDefaults();
        active = true;
        id = ID;
        setImage(i);
    }

    public Turtle(int x, int y, double dir, Image i, Paint color, int ID){
        myPen = new Pen(color);
        setTranslateX(x);
        setTranslateY(y);
        //current = new Point2D(x, y);
        direction = dir;
		initializeTurtleDefaults();
        myPen.changePenState(DEFAULT_DRAW);
        setVisible(DEFAULT_VISIBLE);
        setImage(i);
        active = true;
        id = ID;
    }

    /**
     * Sets up turtle default values to be used in constructors
     */
    private void initializeTurtleDefaults() {
        //mySpeed = DEFAULT_SPEED;
        myMover = new TurtleMover(DEFAULT_SPEED);
        //current = new Point2D(DEFAULT_POS, DEFAULT_POS);
        //goal = new Point2D(DEFAULT_POS, DEFAULT_POS);
        //myGoalQ = new PointQueue();
        setTranslateX(DEFAULT_POS);
        setTranslateY(DEFAULT_POS);
        setFitWidth(DEFAULT_SIZE);
        setFitHeight(DEFAULT_SIZE);
        direction = DEFAULT_POS;
        myPen.changePenState(DEFAULT_DRAW);
        setVisible(DEFAULT_VISIBLE);
        setImage(DEFAULT_IMAGE);
    }
    
    private void setUpBindings (List<ObjectProperty> bindings) {
        myImage = bindings.get(UIManager.TURTLE_IMAGE_INDEX);
        this.imageProperty().bind(myImage);       
        //add pen bindings depending on how you want to set up pen        
    }
    
    private void moveMyself(double x, double y) {
        myMover.newGoal(this, x, y);
    }
    
    public Pen getPen() {
        return myPen;
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

	public void move(double pixels) {
		myMover.move(pixels, direction);
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
		myMover.changeDirection(this, degrees);
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
		myMover.newGoal(this, x, y);
	}
//	
//    public void checkQueue(PointQueue q) {
//    	if (q.size() == 0) {
//    		goal = current;
//    		return;
//    	}
//    	if (current != q.peak()) {
//    		goal = q.peak();
//    		return;
//    	}
//    	if (current == q.peak()) {
//    		q.removeFirst();  
//    		checkQueue(q);
//    	}
//    }
	
	/**
	 * Moves the turtle incrementally towards its goal
	 * Draws a line to the turtle's current position
	 * @param gc
	 * @param shift
	 */
	public void animatedMove(GraphicsContext gc, Point2D shift) {
		myMover.animation(this, myPen, gc, shift);
	}
	
	
//	private Point2D findNextPoint(double x0, double y0, double x1, double y1) {
//		if (atGoal()) {
//			return goal;
//		}
//		Point2D v = new Point2D(x1 - x0, y1 - y0);
//		double vMag = Math.sqrt(Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2));
//		Point2D u = new Point2D(v.getX()/vMag, v.getY()/vMag);
//		Point2D next = new Point2D(x0 + mySpeed*(u.getX()), 
//				y0 + mySpeed*(u.getY()));
//		return next;
//	}
//	
	public Point2D nextFromCurrent() {
		return myMover.nextFromCurrent();
	}
//	
//	private boolean atGoal() {
//		return current == goal;
//	}
	
	public void updatePenAttributes(GraphicsContext gc) {
		gc.setStroke(myPen.getColor());
        gc.setLineWidth(myPen.getSize());
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

}
    


