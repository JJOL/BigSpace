package mdj2.bigspace.game.levels;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mdj2.bigspace.game.resources.ResLoader;

public class LevelLoader {
	
	private int mapData[];
	private int mWidth, mHeight;
	
	private String planetName;
	 
	public LevelLoader(String planetName) {
		this.planetName = planetName;
	}

	public LevelWorld loadWorld(int levelIndex) {
		return loadWorld("prueba" + "_" + levelIndex);
	}
	
	public LevelWorld loadWorld(String levelName) {
		
		BufferedImage levelData = null;
		levelData = ResLoader.go("levels").at(levelName + "_level" + ".bmp").loadBufferedImage();
		
		if (levelData == null) {
			System.err.println("Couldn't Find Level Image: " + levelName);
			System.exit(0);
		}
		
		mWidth  = levelData.getWidth();
		mHeight = levelData.getHeight();
		
		mapData = new int[mWidth*mHeight];
		
		for (int y = 0; y < levelData.getHeight(); y++) {
			for (int x = 0; x < levelData.getWidth(); x++) {
				int data = levelData.getRGB(x, y);
				/*
				int d1, d2, d3;
				d1 = (data & 0xFF0000) >> 16;
				d2 = (data & 0x00FF00) >> 8;
				d3 = (data & 0x0000FF);*/
				
				//assignObject(x, y, d1, d2, d3);
				mapData[y * mWidth + x] = data;
			}
		}
		
		
		return new LevelWorld(levelData.getWidth(), levelData.getHeight(), mapData);
	}
	
	private void assignObject(int x, int y, int d1, int d2, int d3) {
		mapData[y * mWidth + x] = d1;
	}
}
