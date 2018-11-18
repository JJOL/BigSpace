package mdj2.bigspace.game.levels.tiles;

public class TileMap {

	private Tile basicSolidBlock;
	private Tile emptyTile;
	
	public TileMap() {
		basicSolidBlock = new SolidBlock(0xFF000000, "bricks_block");
		emptyTile       = new EmptyTile(0xFFFFFFFF);
	}
	
	public int getTileSize() {
		return 32;
	}
	
	public Tile getTileFromId(int tileId) {
		switch (tileId) {
		case 0xFF000000:
			return basicSolidBlock;
		}
		
		
		return emptyTile;
	}
	
}
