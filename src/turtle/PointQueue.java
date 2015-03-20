// This entire file is part of my masterpiece.
// JAMES MOSCA

package turtle;

import java.util.LinkedList;

import javafx.geometry.Point2D;

public class PointQueue {
	private LinkedList<Point2D> list;
	
	public PointQueue() {
		list = new LinkedList<Point2D>();
	}
	
	public void enqueue(Point2D point) {
		list.addLast(point);
	}
	
	public Point2D dequeue() {
		return list.poll();
	}
	   
	public boolean hasItems() {	      
		return !list.isEmpty();	   
	}
	  
	public int size() {
		return list.size();
	}
	
	public Point2D peak() {
		return list.get(0);
	}
	
	public Point2D peakLast() {
		return list.get(list.size() - 1);
	}
	
	public void removeFirst() {
		list.remove(0);
	}

}
