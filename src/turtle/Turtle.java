package turtle;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import worldController.WorldController;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import command.Command;
//Turtle extends IV but eventually, we want to not give WC or UI the entire Turtle
public class Turtle extends ImageView {

	private static final int DEFAULT_POS = 0;
	private static final boolean DEFAULT_DRAW = true;
	private static final boolean DEFAULT_VISIBLE = true;
	private static final Image DEFAULT_IMAGE = new Image("resources/images/default.jpg");
	
	private double direction;
	private boolean active;
	private int id;
	
	private Pen myPen;
	private Point2D current;
	private Point2D next;
	
	public Turtle(Paint color, int ID) {
		myPen = new Pen(color);
		current = new Point2D(DEFAULT_POS, DEFAULT_POS);
		next = new Point2D(DEFAULT_POS, DEFAULT_POS);
		setTranslateX(DEFAULT_POS);
		setTranslateY(DEFAULT_POS);
		setFitWidth(70);
		setFitHeight(70);
		direction = DEFAULT_POS;
		myPen.changePenState(DEFAULT_DRAW);
		setVisible(DEFAULT_VISIBLE);
		setImage(DEFAULT_IMAGE);
		active = true;
		id = ID;
		
	}

	public Turtle(Image i, Paint color, int ID){
		myPen = new Pen(color);
		setTranslateX(DEFAULT_POS);
		setTranslateY(DEFAULT_POS);
		direction = DEFAULT_POS;
		myPen.changePenState(DEFAULT_DRAW);
		setVisible(DEFAULT_VISIBLE);
		setImage(i);
		active = true;
		id = ID;
	}

	public Turtle(int x, int y, Image i, Paint color, int ID){
		myPen = new Pen(color);
		setTranslateX(x);
		setTranslateY(x);
		direction = DEFAULT_POS;
		myPen.changePenState(DEFAULT_DRAW);
		setVisible(DEFAULT_VISIBLE);
		setImage(i);
		active = true;
		id = ID;
	}

	public Turtle(int x, int y, double dir, Image i, Paint color, int ID){
		myPen = new Pen(color);
		setTranslateX(x);
		setTranslateY(y);
		direction = dir;
		myPen.changePenState(DEFAULT_DRAW);
		setVisible(DEFAULT_VISIBLE);
		setImage(i);
		active = true;
		id = ID;
	}

	public boolean isDrawing() {
		return myPen.penReady();
	}

	public void setDrawing(boolean b){
		myPen.changePenState(b);
	}

	public String act(Command c) {
		return truncate(c.run(this));
	}
	
	public String truncate (double d){
		NumberFormat nf = new DecimalFormat("#.##");
		return nf.format(d);
	}

	public void move (double pixels) {
		System.out.println(getTranslateX() + " " + getTranslateY());
		setTranslateX(getTranslateX() + pixels*Math.cos(radians()));
		setTranslateY(getTranslateY() + pixels*Math.sin(radians()));
		next = new Point2D(getTranslateX(), getTranslateY());
		System.out.println(getTranslateX() + " " + getTranslateY());
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
		if(direction < 0)
			direction = 360 + direction;
		setRotate(direction);
	}

	public double getDirection() {
		return direction;
	}

	public void moveTo(double x, double y){
		setTranslateX(x);
		setTranslateY(y);
	}

	public void drawLine(GraphicsContext gc, double shiftX, double shiftY) {
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
	
	public Pen getPen() {
		return myPen;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public int getID(){
		return id;
	}
	
}
