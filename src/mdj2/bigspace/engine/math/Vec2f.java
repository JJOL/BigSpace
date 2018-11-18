package mdj2.bigspace.engine.math;

public class Vec2f {
	
	public static Vec2f fromAngle(float ang) {
		return new Vec2f((float)Math.cos(ang), (float)Math.sin(ang)*-1);
	}

	public float x, y;
	
	public Vec2f() {
		x = 0f;
		y = 0f;
	}
	
	public Vec2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vec2f(Vec2f copy) {
		x = copy.x;
		y = copy.y;
	}
	
	public float magnitude() {
		return (float)Math.sqrt(x*x + y*y);
	}
	
	public float magnitudeSqrd() {
		return (x*x + y*y);
	}
	
	public Vec2f getNormalized() {
		float mag = magnitude();
		return new Vec2f(x / mag, y / mag);
	}
	
	public Vec2f getAdd(Vec2f other) {
		return new Vec2f(x + other.x, y + other.y);
	}
	
	public Vec2f getAdd(float xx, float yy) {
		return new Vec2f(x + xx, y + yy);
	}
	
	public Vec2f getAdd(float f) {
		return new Vec2f(x + f, y + f);
	}
	
	public Vec2f getSubtract(Vec2f other) {
		return new Vec2f(x - other.x, y - other.y);
	}
	
	public Vec2f getSubtract(float xx, float yy) {
		return new Vec2f(x - xx, y - yy);
	}
	
	public Vec2f getSubtract(float f) {
		return new Vec2f(x - f, y - f);
	}
	
	public Vec2f getScaled(float scalar) {
		return new Vec2f(x * scalar, y * scalar);
	}
	
	public void normalize() {
		float mag = magnitude();
		x /= mag;
		y /= mag;
	}
	
	public void add(Vec2f other) {
		x += other.x;
		y += other.y;
	}
	
	public void add(float xx, float yy) {
		x += xx;
		y += yy;
	}
	
	public void add(float f) {
		x += f;
		y += f;
	}
	
	public void subtract(Vec2f other) {
		x -= other.x;
		y -= other.y;
	}
	
	public void subtract(float xx, float yy) {
		x -= xx;
		y -= yy;
	}
	
	public void subtract(float f) {
		x -= f;
		y -= f;
	}
	
	public void scale(float scalar) {
		x *= scalar;
		y *= scalar;
	}
	
	public float dot(Vec2f other) {
		return x*other.x + y*other.y;
	}
	
	public float getAngle() {
		if (x == 0) {
			return 0f;
		}
		return (float)Math.atan2(y, x);
	}
	
	public int iX() {
		return (int)x;
	}
	
	public int iY() {
		return (int)y;
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}
	
	public void print() {
		float mag = magnitude();
		System.out.println("|"+mag+"|"+"("+x+","+y+")");
	}
}
