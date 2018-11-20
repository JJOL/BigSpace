package mdj2.bigspace.game.levels;

import java.awt.Graphics2D;

import mdj2.bigspace.engine.graphics.GameWorld;
import mdj2.bigspace.engine.math.Vec2f;
import mdj2.bigspace.game.entities.EBoss;
import mdj2.bigspace.game.entities.Player;
import mdj2.bigspace.game.levels.tiles.Tile;
import mdj2.bigspace.game.levels.tiles.TileMap;

public class LevelWorld {

	private GameWorld  world;
	int xOff;
	private boolean started;
	private GameCamera camera;
	
	public LevelWorld(int width, int height, int levelData[]) {
		world = new GameWorld(width, height, levelData, new TileMap());
		started = false;
		camera = new GameCamera(640, 480, world.getMinCorner(), world.getMaxCorner());
		addPlayer(new Player(), new Vec2f(64, 64));
		Tile t = world.getTileAt(new Vec2f(740,5));
		if (t== null) {
			System.out.println("Tile Is Null!");
			return;
		}
		if (t.isSolid())
			System.out.println("Tile is solid!");
		else
			System.out.println("Tile is not solid");
	}
	
	public void addPlayer(Player player, Vec2f pos) {
		// Camera siga al jugador
		camera.watchAtCenter(player);
		
		world.spawn(player, pos);
	}
	
	public void addNextDoor(TriggerDoor door, Vec2f pos) {
		
	}
	
	public void addPrevDoor(TriggerDoor door, Vec2f pos) {
		
	}
	
	public void addSpawner(EnemySpawner spawner, Vec2f pos) {
		
	}
	
	public void addBoss(EBoss boss, Vec2f pos) {
		
	}
	
	public void begin() {
		if (started)
			return;
		
		started = true;
		
		// Do Level Begin stuff like spawn enemys, execute timers, etc
		xOff = 0;
	}
	
	public void render(Graphics2D g) {
		Vec2f v1 = camera.getUpperLeft(),
			  v2 = camera.getBottomRight();
		world.render(g, v1.iX(), v1.iY(), v2.iX(), v2.iY());
	}
		
	public void update() {
		world.update();
		//xOff++;
	}
	
	public boolean isLevelOver() {
		return false;
	}
	
	public int getNextLevel() {
		return 0;
	}
}
 