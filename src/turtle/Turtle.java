package turtle;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import userInterface.UIManager;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import command.Command;
//Turtle extends IV but eventually, we want to not give WC or UI the entire Turtle
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
    private double mySpeed;
    private Pen myPen;
    private Point2D current;
    private Point2D next;
    private Point2D goal;
    private ObjectProperty<Image> myImage;
    private int myBoundaryType;

    public Turtle(List<ObjectProperty> bindings, Paint color, int ID) {
        myPen = new Pen(color);
        initializeTurtleDefaults();
        setUpBindings(bindings);
        active = true;
        id = ID;
        myBoundaryType = 2;
//      xProp = new SimpleDoubleProperty(0);
//      yProp = new SimpleDoubleProperty(0);
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

    private void initializeTurtleDefaults() {
        mySpeed = DEFAULT_SPEED;
        current = new Point2D(DEFAULT_POS, DEFAULT_POS);
        next = new Point2D(DEFAULT_POS, DEFAULT_POS);
        goal = new Point2D(DEFAULT_POS, DEFAULT_POS);
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
    	System.out.println("move " + pixels);
//      System.out.println(getTranslateX() + " " + getTranslateY());
//      setTranslateX(getTranslateX() + pixels*Math.cos(radians()));
//      setTranslateY(getTranslateY() + pixels*Math.sin(radians()));
//      next = new Point2D(getTranslateX(), getTranslateY());
//      System.out.println(getTranslateX() + " " + getTranslateY());
//        goal = new Point2D(getTranslateX() + pixels*Math.cos(radians()),
//                getTranslateY() + pixels*Math.sin(radians()));
    	
        goal = new Point2D(goal.getX() + pixels*Math.cos(radians()),
                goal.getY() + pixels*Math.sin(radians()));
  
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
        goal = new Point2D(x, y);
//      while(current != goal) {
//          animatedMove(myGC, shiftX, shiftY);
//      }
    }

    public void previousDrawLine(GraphicsContext gc, double shiftX, double shiftY) {
        double currX = current.getX();
        double currY = current.getY();
        double nextX = getTranslateX();
        double nextY = getTranslateY();
        setTranslateX(nextX);
        setTranslateY(nextY);
        if (myPen.penReady()) {
            gc.strokeLine(currX + shiftX, currY + shiftY, 
                    next.getX() + shiftX, next.getY() + shiftY);
        }
        current = new Point2D(nextX, nextY);
    }
    
    public void animatedMove(GraphicsContext gc, double shiftX, double shiftY) {
        double currX = current.getX();
        double currY = current.getY();
        if (current.distance(goal) < mySpeed) {
            moveMyself(goal.getX(), goal.getY());
            gc.strokeLine(currX + shiftX, currY + shiftY, 
                    goal.getX() + shiftX, goal.getY() + shiftY);
            current = goal;
            return;
        }
        Point2D nextPoint = findNextPoint(currX, currY, 
                goal.getX(), goal.getY());
        moveMyself(nextPoint.getX(), nextPoint.getY());
        if (myPen.penReady()) {
            gc.strokeLine(currX + shiftX, currY + shiftY, 
                    nextPoint.getX() + shiftX, nextPoint.getY() + shiftY);
        }
        current = nextPoint;
    }
    
    private void moveMyself(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
    }
    
    public Point2D findNextPoint(double x0, double y0, double x1, double y1) {
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
    
    public void updatePenAttributes(GraphicsContext gc) {
        gc.setStroke(myPen.getColor());
        gc.setLineWidth(myPen.getSize());
    }
    
    public Pen getPen() {
        return myPen;
    }
    
    public boolean atGoal() {
        return current == goal;
    }
    
    public boolean isActive(){
        return active;
    }
    
    public int getID(){
        return id;
    }
    
    public Point2D getCurrent() {
        return current;
    }
    
    public Point2D getGoal() {
        return goal;
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


    public String truncate (double d){
        NumberFormat nf = new DecimalFormat("#.##");
        return nf.format(d);
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

    public void setActive(boolean b) {
        active = b;

    }
    public void makeBoundary(int boundaryType) {
        myBoundaryType = boundaryType;
    }
    
    public int getBoundaryType() {
        return myBoundaryType;
    }
    
    public void setGoal(Point2D newGoal) {
        goal = newGoal;
    }
}