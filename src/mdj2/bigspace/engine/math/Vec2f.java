package mdj2.bigspace.engine.math;

public class Vec2f {
	
	public static Vec2f fromAngle(float ang) {
		return new Vec2f((float)Math.cos(ang), (float)Math.sin(ang));
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
	
	public void getNormalized() {
		float mag = magnitude();
		x /= mag;
		y /= mag;
	}
	
	public void getAdd(Vec2f other) {
		x += other.x;
		y += other.y;
	}
	
	public void getAdd(float xx, float yy) {
		x += xx;
		y += yy;
	}
	
	public void getAdd(float f) {
		x += f;
		y += f;
	}
	
	public void getSubtract(Vec2f other) {
		x -= other.x;
		y -= other.y;
	}
	
	public void getSubtract(float xx, float yy) {
		x -= xx;
		y -= yy;
	}
	
	public void getSubtract(float f) {
		x -= f;
		y -= f;
	}
	
	public void getScaled(float scalar) {
		x *= scalar;
		y *= scalar;
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
}
