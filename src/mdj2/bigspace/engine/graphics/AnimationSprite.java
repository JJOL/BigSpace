package mdj2.bigspace.engine.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AnimationSprite {

	private int imageIndex;
	private CachedSprite sprite;
	
	private boolean reachedEnd;
	private int animSpd;
	private int animCount;
	private int topCount;
	
	private int sX, sY;
	
	public AnimationSprite(int x, int y, String name) {
		imageIndex = 0;
		sprite = Sprites.getSprite(name);
		System.out.println("Sprites Amount:"+ sprite.size());
		sX = x;
		sY = y;
	}
	
	public void setX(int x) {
		sX = x;
	}
	
	public void setY(int y) {
		sY = y;
	}
	
	public void setAnimSpd(int animSpd) {
		this.animSpd = animSpd;
		if (animSpd != 0) {
			topCount = 60 / animSpd;
			animCount = 0;
		}
		reachedEnd = false;
	}

	public void update() {
		if (animSpd > 0) {
			if (animCount < topCount) {
				animCount++;
				reachedEnd = false;
			} else {
				animCount = 0;
				imageIndex = (imageIndex+1) % sprite.size();
				reachedEnd = true;
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite.get(imageIndex), sX, sY, null);
	}
	
	public boolean hasReachedEnd() {
		return reachedEnd;
	}
	
	public void reset() {
		imageIndex = 0;
		animCount = 0;
	}
	
	
}
