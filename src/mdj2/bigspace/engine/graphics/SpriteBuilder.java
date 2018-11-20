package mdj2.bigspace.engine.graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import mdj2.bigspace.game.resources.ResLoader;

public class SpriteBuilder {
	
	private BufferedImage spritesheet;
	private List<BufferedImage> images;
	private int cellW, cellH;

	public SpriteBuilder (String fName, int cellW, int cellH) {
		spritesheet = ResLoader.go(fName).loadBufferedImage();
		images = new ArrayList<>();
		this.cellW = cellW;
		this.cellH = cellH;
	}
	
	public SpriteBuilder addImage(int i, int j) {
		images.add(spritesheet.getSubimage(i*cellW, j*cellH, cellW, cellH));
		return this;
	}
	
	public CachedSprite build() {
		return new CachedSprite(images);
	}
}
