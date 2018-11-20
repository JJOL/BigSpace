package mdj2.bigspace.game.levels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import mdj2.bigspace.engine.graphics.GameWorld;
import mdj2.bigspace.engine.math.Vec2f;
import mdj2.bigspace.game.entities.EBoss;
import mdj2.bigspace.game.entities.player.Player;
import mdj2.bigspace.game.levels.tiles.Tile;
import mdj2.bigspace.game.levels.tiles.TileMap;
import mdj2.bigspace.game.resources.ResLoader;

public class LevelWorld {

	private GameWorld  world;
	int xOff;
	private boolean started;
	private GameCamera camera;
	
	Vec2f bUL;
	int bW, bH;
	BufferedImage background;
	
	private TriggerDoor nextDoor;
	private boolean levelOver;
	
	private final int TOP_SECS = 45;
	private int timeSecsCounter;
	private int tickCounter;
	
	public LevelWorld(int width, int height, int levelData[]) {
		timeSecsCounter = TOP_SECS;
		tickCounter = 0;
		
		world = new GameWorld(width, height, levelData, new TileMap());
		started = false;
		levelOver = false;
		camera = new GameCamera(640, 480, world.getMinCorner(), world.getMaxCorner());
		//addPlayer(new Player(), new Vec2f(64, 64));
		Tile t = world.getTileAt(new Vec2f(740,5));
		if (t== null) {
			System.out.println("Tile Is Null!");
			return;
		}
		if (t.isSolid())
			System.out.println("Tile is solid!");
		else
			System.out.println("Tile is not solid");
		
		bUL = new Vec2f();
		bW = 760;
		bH = 570;
		background = ResLoader.go("images").at("backgrounds").at("fire_back.jpg").loadBufferedImage();
	}
	
	public void addPlayer(Player player, Vec2f pos) {
		// Camera siga al jugador
		camera.watchAtCenter(player);
		
		world.spawn(player, pos);
	}
	
	public void addNextDoor(TriggerDoor door, Vec2f pos) {
		nextDoor = door;
		world.spawn(door, pos);
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
	
	public float match (float var, float x1, float x2, float y1, float y2) {
		float prop;
		prop = (x2-x1) / (var-x1);
		return y1 + (y2-y1)*prop;
	}
	
	public void render(Graphics2D g) {
		
		
		Vec2f p = world.getMinCorner();
		Vec2f q = (world.getMaxCorner().getSubtract(640, 480));
		Vec2f r = camera.getUpperLeft();
		
		float tx = r.x / q.x;
		int px = (int)((1 - tx)*(r.x - (760-640)/2));
		//x = match(r.x, p.x, q.x, )
		float ty = r.y / q.y;
		int py = (int)((1 - ty)*(r.y - (570-480)/2));
		
		px = 0;
		py = 0;
		
		g.drawImage(background, px, py, bW, bH, null);
		
		Vec2f v1 = camera.getUpperLeft(),
			  v2 = camera.getBottomRight();
		
		// Render Level Background?
		
		world.render(g, v1.iX(), v1.iY(), v2.iX(), v2.iY());
		
		g.setColor(Color.WHITE);
		g.drawString("Time Left: " + timeSecsCounter + "s", 0, 0);
		
	}
		
	public void update() {
		world.update();
		//xOff++;
		if (nextDoor != null && nextDoor.activated()) {
			levelOver = true;
		}
		
		if (tickCounter >= 60) {
			tickCounter = 0;
			timeSecsCounter--;
		} else {
			tickCounter++;
		}
	}
	
	public boolean isLevelOver() {
		return levelOver;
	}
	
	public int getNextLevel() {
		return 1;
	}
	
	
	public int getTime() {
		return timeSecsCounter;
	}
}
 