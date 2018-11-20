package mdj2.bigspace.engine.gameobjects;

import java.awt.Rectangle;

import mdj2.bigspace.engine.math.Vec2f;

public class RectCollider extends Collider {

	private int oW, oH;
	
	public RectCollider(GameObject obj, int pWidth, int pHeight) {
		super(obj);
		//pos = baseObject.getPos();
		oW = pWidth;
		oH = pHeight;
	}

	public Vec2f getTop() {
		Vec2f objPos = baseObject.getPos();
		return objPos.getSubtract(0, oH/2);
	}
	
	public Vec2f getBottom() {
		Vec2f objPos = baseObject.getPos();
		return objPos.getAdd(0, oH/2);
	}

	public Vec2f getLeft() {
		Vec2f objPos = baseObject.getPos();
		return objPos.getSubtract(oW/2, 0);
	}
	
	public Vec2f getRight() {
		Vec2f objPos = baseObject.getPos();
		return objPos.getAdd(oW/2, 0);
	}
	
	public Rectangle getRect() {
		Vec2f pos = baseObject.getPos().getSubtract(oW/2, oH/2);
		return new Rectangle(pos.iX(), pos.iY(), oW, oH);
	}
	
	@Override
	public boolean collides(Collider c) {
		if (c instanceof RectCollider)
			return ((RectCollider)c).getRect().intersects(getRect());
		return false;
	}
}
