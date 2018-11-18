package mdj2.bigspace.engine.physics;

import mdj2.bigspace.engine.gameobjects.GameObject;
import mdj2.bigspace.engine.graphics.GameWorld;
import mdj2.bigspace.engine.math.Vec2f;

public class PhysicsSim {

	public static final Vec2f FALL_UNIT_VEC = Vec2f.fromAngle((float)Math.toRadians(270));
	
	// Configured Enviornment
	private float gravityMag;
	private Vec2f gravityVec;
	
	private float FALLING_MAX_SPEED;
	
	private GameWorld world;
	
	public PhysicsSim(GameWorld world) {
		this.world = world;
		FALLING_MAX_SPEED = 5;
		gravityMag = 0.05f;
		setup();
	}
	
	public void setup() {
		gravityVec = FALL_UNIT_VEC.getScaled(gravityMag);
	}
	
	public void applyGravity(GameObject obj) {
		if (obj.getVel().dot(FALL_UNIT_VEC) < FALLING_MAX_SPEED) {
			obj.getAcc().add(gravityVec.getScaled(obj.getMass()));
		}
	}
	
	public void testSolidCollisions(GameObject obj) {
		 
		// Vertical Down Collision
		/*boolean collides = false;
		
		
		// Test if collider position + velocity collides againt the next solid tile
		Collider col = obj.getCollider();
		Vec2f oVel = obj.getVel();
		
		
		{
			// Get Corresponding face to direction
			// VelY < 0 topFace
			// VelY > 0 btmFace
			// VelX < 0 leftFace
			// VelX   0 rightFace
			delta = dirVer.dot(oVel)
			dest = col.getFace(dir) + delta
		
		// Consider the closest solid tile for the object, otherwise, after dest
		int blockDist = toTile1D(delta)
		Vec2f currBlock = toTile2D(colPos)
		for (int i = 1; i < blockDist; i++)
			nextTile = world.getTile(currBlock + (0,i))
			if (next.isSolid(nextTile))
				break
		
		// Check if after movement it surpases the closest solid tile
		if (dest > nextTile.top) 
			if (isSolid(nextTile)) {
				collides = true;
			}
		
		if (collides) {
			// If it was moving, notify it collided
			if (obj.getVel().y > 0) {
				notifyCollision(obj);
			}
			
			// Limit Velocity to not pass over the solid
			obj.getVel().y = topBlock - btmCollider;		
		}
		}
		//*/
	}
	
	public void updateMovement(GameObject obj) {
		obj.getPos().add(obj.getVel());
		obj.getVel().add(obj.getAcc());
		obj.setAcc(new Vec2f());
	}
}
