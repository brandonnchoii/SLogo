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
	
	public void removeFirst() {
		list.remove(0);
	}
//	public void addItems(PointQueue<? extends Point2D> q) {
//		while (q.hasItems()) {
//			list.addLast(q.dequeue());
//	   }
//	}
}
