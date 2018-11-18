package mdj2.bigspace.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import mdj2.bigspace.engine.GameScene;
import mdj2.bigspace.engine.graphics.GameWorld;
import mdj2.bigspace.game.levels.tiles.SolidBlock;
import mdj2.bigspace.game.levels.tiles.Tile;
import mdj2.bigspace.game.levels.tiles.TileMap;

public class TestPlayScene extends GameScene{

	GameWorld world;
	
	public TestPlayScene () {
		TileMap tileMap = new TileMap() {
			SolidBlock blueTile = new SolidBlock(1, "") {
				@Override
				public BufferedImage getTexImg() {
					BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_3BYTE_BGR);
					Graphics g = img.createGraphics();
					g.setColor(Color.BLUE);
					g.fillRect(0, 0, 32, 32);
					g.dispose();
					
					return img;
				}
			};
			SolidBlock redTile = new SolidBlock(1, "") {
				@Override
				public BufferedImage getTexImg() {
					BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_3BYTE_BGR);
					Graphics g = img.createGraphics();
					g.setColor(Color.RED);
					g.fillRect(0, 0, 32, 32);
					g.dispose();
					
					return img;
				}
			};
			@Override
			public Tile getTileFromId(int tileId) {
				return tileId == 1 ? blueTile : redTile;
			}
		};
		int tiles[] = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 
								1, 1, 2, 1, 1, 2, 1, 1,
								1, 1, 2, 1, 1, 2, 1, 1,
								1, 1, 1, 2, 2, 1, 1, 1};
		world = new GameWorld(8, 4, tiles, tileMap);
		world.spawn(new TestObject(world));
	}
	
	@Override
	public void sRender(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 640, 480);
		world.render(g, 0, 0, 640, 480);
	}

	@Override
	public void sUpdate() {
		world.update();
	}

	
	
}
