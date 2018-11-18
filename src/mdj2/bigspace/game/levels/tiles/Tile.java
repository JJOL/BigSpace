package mdj2.bigspace.game.levels.tiles;

import java.awt.image.BufferedImage;

public class Tile {

	private final int tileId;
	
	private boolean solid;
	private boolean visible;
	
	public Tile(int _tileId) {
		tileId = _tileId;
		solid = false;
	}
	
	protected void setSolid(boolean _solid) {
		solid = _solid;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public BufferedImage getTexImg() { return null; }
}
