package mdj2.bigspace.engine.math;

import java.util.ArrayList;

public class QuadTree<T> {

	private int x1, y1, x2, y2;
	private int width, height;
	
	private static final int MAX_NODE_CAPACITY = 5;
	
	private ArrayList<T>[] quadrants; 
	public QuadTree (int _x1, int _y1, int _x2, int _y2) {
		x1 = _x1;
		y1 = _y1;
		x2 = _x2;
		y2 = _y2;
		
		width  = x2 - x1;
		height = y2 - y1;
		
		initCuadrants();
	}
	
	private void initCuadrants() {
		quadrants = new ArrayList[4];
		
		for (int i = 0; i < quadrants.length; i++) {
			quadrants[i] = new ArrayList<T>(MAX_NODE_CAPACITY);
		}
	}
	
	
	public void insert(T obj, int x, int y) {
		
	}
	
}
