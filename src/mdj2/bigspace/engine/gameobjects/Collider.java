package mdj2.bigspace.engine.gameobjects;

import java.awt.Rectangle;

import mdj2.bigspace.engine.math.Vec2f;

public class Collider extends ObjectComponent{

	public Rectangle r;
	
	private Vec2f center;
	private float top, right, bottom, left;
	private float width, height;
	
	public Collider(GameObject obj) {
		super(obj);
	}
	
	public Vec2f getCenter() {
		return center;
	}
	
	public float getXRad() {
		return getWidth() / 2;
	}
	
	public float getYRad() {
		return getHeight() / 2;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getFace(int direction) {
		float coord;
		
		switch (direction) {
		case 0:
			coord = top;
			break;
		case 1:
			coord = right;
			break;
		case 2:
			coord = bottom;
			break;
		case 3:
			coord = left;
			break;
		default:
			coord = top; // Return top
		}
		
		return coord;
	}
	
	public boolean collides (Collider c) {
		return false;
	}
	
	public Vec2f getTop() {
		return new Vec2f();
	}
	
	public Vec2f getBottom() {
		return new Vec2f();
	}

	public Vec2f getLeft() {
		return new Vec2f();
	}
	
	public Vec2f getRight() {
		return new Vec2f();
	}
	
}
