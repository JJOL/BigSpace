package mdj2.bigspace.engine.physics;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mdj2.bigspace.engine.gameobjects.Collider;
import mdj2.bigspace.engine.gameobjects.GameObject;
import mdj2.bigspace.engine.graphics.GameWorld;
import mdj2.bigspace.engine.math.Vec2f;
import mdj2.bigspace.game.levels.tiles.Tile;

public class PhysicsSim {

	public static final Vec2f FALL_UNIT_VEC = Vec2f.fromAngle((float)Math.toRadians(270));
	public static final Vec2f RIGHT_UNIT_VEC = Vec2f.fromAngle(0);
	
	// Configured Enviornment
	private float gravityMag;
	private Vec2f gravityVec;
	
	private float FALLING_MAX_SPEED;
	
	private GameWorld world;
	
	private Map<String, Integer> collObjMap;
	
	public PhysicsSim(GameWorld world) {
		this.world = world;
		collObjMap = new HashMap<>();
		
		FALLING_MAX_SPEED = 10;
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
		
		Collider col = obj.getCollider();
		if (col == null)
			return;
		
		// For Vertical Motion
		Vec2f oVel = obj.getVel();
		Vec2f cPos;
		{
			Vec2f dirVer = FALL_UNIT_VEC;
			float delta;
			float dest;
			
			delta = dirVer.dot(oVel);
			Rectangle colRect = col.getRect();
			colRect.translate(0, (int)(delta+Math.signum(delta)));
			List<Tile> tiles = world.getIntersectTiles(colRect);
			boolean collision = false;
			for (Tile tile : tiles) {
				if (tile.isSolid()) {
					collision = true;
					break;
				}
			}
			if (collision) {
				obj.getVel().y = 0;
				//obj.getVel().y = -2*Math.signum(delta);
			}
		}
		// For Horizontal Motion
		{
			Vec2f dirVer = RIGHT_UNIT_VEC;
			float delta;
			float dest;
			
			delta = dirVer.dot(oVel);
			Rectangle colRect = col.getRect();
			colRect.translate((int)(delta), 0);
			List<Tile> tiles = world.getIntersectTiles(colRect);
			boolean collision = false;
			for (Tile tile : tiles) {
				if (tile.isSolid()) {
					collision = true;
					break;
				}
			}
			if (collision) {
				obj.getVel().x = 0;
				//obj.getVel().x = -2*Math.signum(delta);
			}
		}
		// Test if collider position + velocity collides againt the next solid tile
		
		
		/*
		if (delta > 0) {
			// Going Down
			cPos = col.getBottom();
			dest = cPos.y + delta;
		} else {
			cPos = col.getTop();
			dest = cPos.y - delta;
		}
		
			
		// Consider the closest solid tile for the object, otherwise, after dest
		int tileSize = world.getTileSize();
		int blockDist = (int)(delta / tileSize)+1;
		//System.out.println("Checking blocks: " + blockDist);
		Tile nextTile = null,
			 aux      = null;
		int i;
		for (i = 1; i <= blockDist; i++) {
			aux = world.getTileAt(cPos.getAdd(0, i * tileSize));
			if (aux != null && aux.isSolid()) {
				nextTile = aux;
				System.out.println("Found Solid Block");
				break;
			}
		}
		
		if (nextTile == null)
			return;
		
		
		
		// Check if after movement it surpases the closest solid tile
		int topBlock = (int)(cPos.getAdd(0, i * tileSize).y / 32) * 32;
		if (dest > topBlock) {
			System.out.println("Collision");
			// If it was moving, notify it collided
			if (obj.getVel().y > 0) {
				//notifyCollision(obj);
			}
			
			// Limit Velocity to not pass over the solid
			obj.getVel().y = topBlock - cPos.y;	
			obj.getVel().y = 0;
		}*/
		
	}
	
	public void testObjectCollision(GameObject obj) {
		for (GameObject other : world.getObjects()) {
			if (obj.equals(other))
				continue;
			
			if (obj.getCollider().collides(other.getCollider())) {
				if (!collRegistered(obj, other)) {
					collRegister(obj, other);
					obj.onCollision(other);
					other.onCollision(obj);
				}
			}
		}
	}
	
	private boolean collRegistered(GameObject obj1, GameObject obj2) {
		int id1 = obj1.getId(),
			id2 = obj2.getId();
		
		if (id1 > id2) {
			return collObjMap.containsKey(id1 + "a-a" + id2);
		} else {
			return collObjMap.containsKey(id2 + "a-a" + id1);
		}
	}
	
	private void collRegister(GameObject obj1, GameObject obj2) {
		int id1 = obj1.getId(),
			id2 = obj2.getId();
		
		if (id1 > id2) {
			collObjMap.put(id1 + "a-a" + id2, 1);
		} else {
			collObjMap.put(id2 + "a-a" + id1, 1);
		}
	}
	
	public void updateMovement(GameObject obj) {
		obj.getPos().add(obj.getVel());
		obj.getVel().add(obj.getAcc());
		obj.setAcc(new Vec2f());
		//obj.notifyMoved();
	}
	
	public void resetFrame() {
		collObjMap.clear();
	}
}
