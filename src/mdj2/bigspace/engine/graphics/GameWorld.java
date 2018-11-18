package mdj2.bigspace.engine.graphics;

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
	
	private PhysicsSim physics;
	
	private int tileStructure[];
	private TileMap tileMap;
	private int tWidth, tHeight;
	
	public GameWorld(int w_width, int w_height, int tileStructure[], TileMap tileMap) {
		this.tileStructure = tileStructure;
		this.tileMap = tileMap;
		spawnQueue = new LinkedBlockingQueue<>();
		physics = new PhysicsSim(this);
		
		tWidth  = w_width;
		tHeight = w_height; 
	}
	
	public void spawn(GameObject obj) {
		spawnQueue.add(obj);
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
		/*int tX1 = nearestTileX(x1),
			tY1 = nearsetTileY(y1),
			tX2 = nearestTileX(x2),
			tY2 = nearestTileY(y2);
		
		for (int ty = tY1; ty < tY2; ty++) {
			for (int tx = tX1; tx < tX2; tx++) {
				Tile tile = tileMap.getTileFromId(tileStructure[ty*tWidth + tx]);
				tile.
			}
		}*/
	}
	
	public void update() {
		
		// Spawn Requested Objects
		while (!spawnQueue.isEmpty()) {
			GameObject newObj = spawnQueue.poll();
			newObj.onWorldSpawn();
			objects.add(newObj);
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
		
		// Update Object Physics
		itr = objects.iterator();
		while (itr.hasNext()) {
			GameObject obj = itr.next();

			physics.applyGravity(obj);
			physics.testSolidCollisions(obj);
			physics.updateMovement(obj);
			
		}
	}
}
