package mdj2.bigspace.engine.graphics;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import mdj2.bigspace.engine.gameobjects.GameObject;
import mdj2.bigspace.engine.math.Vec2f;
import mdj2.bigspace.engine.physics.PhysicsSim;
import mdj2.bigspace.game.levels.tiles.Tile;
import mdj2.bigspace.game.levels.tiles.TileMap;

public class GameWorld {
	
	private List<GameObject> objects = new LinkedList<>();
	private Queue<GameObject> spawnQueue;
	
	private Map<GameObject, Vec2f> objSpawnPosMap;
	
	private PhysicsSim physics;
	
	private int tileStructure[];
	private TileMap tileMap;
	private int tWidth, tHeight;
	
	public GameWorld(int w_width, int w_height, int tileStructure[], TileMap tileMap) {
		this.tileStructure = tileStructure;
		this.tileMap = tileMap;
		spawnQueue = new LinkedBlockingQueue<>();
		objSpawnPosMap = new HashMap<>();
		physics = new PhysicsSim(this);
		
		tWidth  = w_width;
		tHeight = w_height; 
	}
	
	public List<GameObject> getObjects(){
		return objects;
	}
	public int getTileSize() {
		return tileMap.getTileSize();
	}
	
	public Tile getTileAt(Vec2f wPos) {
		int tSize = getTileSize();
		System.out.println("Max X:" + tWidth*tSize);
		if (wPos.x < 0 || wPos.y < 0 || wPos.x > tWidth*tSize || wPos.y > tHeight*tSize)
			return null;
		
		int tx = wPos.iX() / tSize,
			ty = wPos.iY() / tSize;
		
		return tileMap.getTileFromId(tileStructure[ty * tWidth + tx]);
	}
	
	public void spawn(GameObject obj, Vec2f pos) {
		spawnQueue.add(obj);
		objSpawnPosMap.put(obj, pos);
	}
	
	public void spawn(GameObject obj) {
		spawn(obj, new Vec2f(0, 0));
	}
	
	public void render(Graphics2D g, int x1, int y1, int x2, int y2) {
		// Translate Graphics for Objects
		// (fX,fY) = (vx,vy) + (bx,by) = (0,0)
		// Only when (vx+bx) = 0 and (vy+by) = 0
		// Thus (bx,by) must be the negative of where we want to start drawing (-vx,-vy) 
		g.translate(-x1, -y1);
		worldRender(g, x1, y1, x2, y2);
		renderGameObjects(g, x1, y1, x2, y2);
		
		// Reposition Graphics in Window Origin for other Scene Rendering
		
		g.translate(0, 0);
		
		fixedRender(g);
	}
	
	public void renderGameObjects(Graphics2D g, int x1, int y1, int x2, int y2) {
		Iterator<GameObject> itr = objects.iterator();
		while (itr.hasNext()) {
			//System.out.println("World Rendering");
			GameObject obj = itr.next();
			Vec2f objPos = obj.getPos();
			int ox = objPos.iX(), oy = objPos.iY();
			if (ox >= x1 && ox <= x2 && oy >= y1 && oy <= y2)
				obj.render(g);
		}
	}
	
	public void worldRender(Graphics2D g, int x1, int y1, int x2, int y2) {
		renderTiles(g, x1, y1, x2, y2);
	}
	
	public void fixedRender(Graphics2D g) {
		
	}
	
	public void renderTiles(Graphics2D g, int x1, int y1, int x2, int y2) {
		for (int i = 0; i < tHeight; i++) {
			for (int j = 0; j < tWidth; j++) {
				int color = tileStructure[i*tWidth + j];
				//if (color < 0xFF333333)
					//color = 0xFF000000;
				
				Tile tile = tileMap.getTileFromId(color);
				if (tile.getVisible()) {
					int size = tileMap.getTileSize();
					g.drawImage(tile.getTexImg(), j*size, i*size, size, size, null);
				}
			}
		}
		
	}
	
	public void update() {
		 
		// Spawn Requested Objects
		while (!spawnQueue.isEmpty()) {
			GameObject newObj = spawnQueue.poll();
			newObj.onSpawn(this, objSpawnPosMap.get(newObj));
			objSpawnPosMap.remove(newObj);
			objects.add(newObj);
			
			// For Subclasses of gameobjects
			newObj.onWorldSpawn();
		}
		
		// Update Objects
		Iterator<GameObject> itr = objects.iterator();
		while (itr.hasNext()) {
			GameObject obj = itr.next();
			obj.update();
		}
		
		// Remove Deleted Objects
		itr = objects.iterator();
		while (itr.hasNext()) {
			GameObject obj = itr.next();
			if (obj.shouldBeRemoved()) {
				obj.onWorldRemove();
				itr.remove();
			}
		}
		
		physics.resetFrame();
		// Update Object Physics
		itr = objects.iterator();
		while (itr.hasNext()) {
			GameObject obj = itr.next();

			physics.applyGravity(obj);
			physics.testSolidCollisions(obj);
			physics.updateMovement(obj);
			
		}
	}
	
	public Vec2f getMinCorner() {
		return new Vec2f(0,0);
	}
	
	public Vec2f getMaxCorner() {
		return new Vec2f(tileMap.getTileSize()*tWidth, tileMap.getTileSize()*tHeight);
	}
	
	// World Query Methods
	
	public GameObject findObjectById(int id) {
		for (GameObject obj : objects) {
			if (obj.getId() == id)
				return obj;
		}
		
		return null;
	}
	
	public GameObject findObjectByType (Class<GameObject> objType) {
		for (GameObject obj : objects) {
			if (obj.getClass().isInstance(objType)) {
				return obj;
			}
		}
		
		return null;
	}
	
	public List<GameObject> findAllObjectsByType(Class<GameObject> objType) {
		List<GameObject> resObjs = new ArrayList<>();
		for (GameObject obj : objects) {
			if (obj.getClass().isInstance(objType)) {
				resObjs.add(obj);
			}
		}
		
		return resObjs;
	}
}
