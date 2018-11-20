package mdj2.bigspace.game.levels;

import mdj2.bigspace.engine.graphics.CameraObservable;
import mdj2.bigspace.engine.math.Vec2f;

public class GameCamera {

	private int width, height;
	
	private Vec2f upperLeft, bottomRight;
	
	private Vec2f minCorner, maxCorner;
	
	public GameCamera(int _width, int _height, Vec2f _minCorner, Vec2f _maxCorner) {
		width  = _width;
		height = _height;
		
		minCorner = _minCorner;
		maxCorner = _maxCorner;
		
		upperLeft = new Vec2f(0, 0);
		bottomRight = upperLeft.getAdd(width, height);
	}

	public Vec2f getUpperLeft() {
		return upperLeft;
	}

	public Vec2f getBottomRight() {
		return bottomRight;
	}
	
	public void lookAt(Vec2f vec) {
		upperLeft   = vec.getSubtract(320, 240);
		bottomRight = upperLeft.getAdd(width, height);
		
		if (upperLeft.x < minCorner.x)
			setUpperLeft(new Vec2f(minCorner.x, upperLeft.y));
		if (upperLeft.y < minCorner.y)
			setUpperLeft(new Vec2f(upperLeft.x, minCorner.y));
		
		if (bottomRight.x > maxCorner.x)
			setBottomRight(new Vec2f(maxCorner.x, bottomRight.y));
		if (bottomRight.y > maxCorner.y)
			setBottomRight(new Vec2f(bottomRight.x, maxCorner.y));
		
	}
	
	public void setUpperLeft(Vec2f v) {
		upperLeft = v;
		bottomRight = upperLeft.getAdd(width, height);
	}
	
	public void setBottomRight(Vec2f v) {
		bottomRight = v;
		upperLeft = bottomRight.getSubtract(width, height);
	}

	public void watchAtCenter(CameraObservable observable) {
		observable.addCamera(this);
	}
}
