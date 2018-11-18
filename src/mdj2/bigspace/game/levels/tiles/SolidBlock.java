package mdj2.bigspace.game.levels.tiles;

import java.awt.image.BufferedImage;

import mdj2.bigspace.game.resources.ResLoader;

public class SolidBlock extends Tile{

	private BufferedImage texImg;
	
	public SolidBlock(int _tileId, String texName) {
		super(_tileId);
		setSolid(true);
		setVisible(true);
		
		texImg = ResLoader.go("textures").at(texName+".jpg").loadBufferedImage();
	}
	
	public BufferedImage getTexImg() {
		return texImg;
	}
	
	

}
