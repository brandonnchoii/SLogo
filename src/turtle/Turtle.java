package turtle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import command.Command;

public class Turtle extends ImageView {

	private double direction;
	private Pen myPen;
	private static final int DEFAULT_POS = 0;
	private static final boolean DEFAULT_DRAW = true;
	private static final boolean DEFAULT_VISIBLE = true;
	private static final Image DEFAULT_IMAGE = new Image("resources/images/default.jpg");
	
	public Turtle(Paint color) {
		myPen = new Pen(color);
		setTranslateX(DEFAULT_POS);
		setTranslateY(DEFAULT_POS);
		direction = DEFAULT_POS;
		myPen.changePenState(DEFAULT_DRAW);
		setVisible(DEFAULT_VISIBLE);
		setImage(DEFAULT_IMAGE);

	}

	public Turtle(Image i, Paint color){
		myPen = new Pen(color);
		setTranslateX(DEFAULT_POS);
		setTranslateY(DEFAULT_POS);
		direction = DEFAULT_POS;
		myPen.changePenState(DEFAULT_DRAW);
		setVisible(DEFAULT_VISIBLE);
		setImage(i);
	}

	public Turtle(int x, int y, Image i, Paint color){
		myPen = new Pen(color);
		setTranslateX(x);
		setTranslateY(x);
		direction = DEFAULT_POS;
		myPen.changePenState(DEFAULT_DRAW);
		setVisible(DEFAULT_VISIBLE);
		setImage(i);
	}

	public Turtle(int x, int y, double dir, Image i, Paint color){
		myPen = new Pen(color);
		setTranslateX(x);
		setTranslateY(y);
		direction = dir;
		myPen.changePenState(DEFAULT_DRAW);
		setVisible(DEFAULT_VISIBLE);
		setImage(i);
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
		setTranslateX(getTranslateX() + pixels*Math.cos(radians()));
		setTranslateY(getTranslateY() + pixels*Math.sin(radians()));
	}

	private double radians(){
		return Math.toRadians(direction);
	}

	public void fixPos(double height, double width){
		setTranslateX(getTranslateX() % width);
		setTranslateY(getTranslateY() % height);
	}

	public void rotate (double degrees) {
		direction += degrees;
	}

	public void setHeading (double degrees) {
		direction = degrees % 360;
		if(direction < 0)
			direction = 360 + direction;
	}

	public double getDirection() {
		return direction;
	}

	public void moveTo(double x, double y){
		setTranslateX(x);
		setTranslateY(y);
	}

	public Paint drawWithColor(Paint color) {
		return myPen.getColor();
	}
	
	public double drawWithSize(double size) {
		return myPen.getSize();
	}
	
}
