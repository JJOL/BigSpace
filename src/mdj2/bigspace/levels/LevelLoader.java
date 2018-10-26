package mdj2.bigspace.levels;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class LevelLoader {
	
	

	public void load(String levelName) {
		/*File levelFile = new File(levelName);
		if (!levelFile.exists()) {
			System.err.println("File " + levelName + " doesn't exist!");
			System.exit(0);
		}
		
		BufferedImage levelData = ImageIO.read(levelFile);
		if (levelData == null) {
			System.err.println("Couldn't Find Level Image: " + levelName);
			System.exit(0);
		}
		
		for (int y = 0; y < levelHeight; y++) {
			for (int x = 0; x < levelWidth; x++) {
				int data = levelData.getRGB(x, y);
				
				int d1, d2, d3;
				d1 = (data & 0xFF0000) >> 16;
				d2 = (data & 0x00FF00) >> 8;
				d3 = (data & 0x0000FF);
				
				assignObject(x, y, d1, d2, d3);
			}
		}*/
		
	}
	
	private void assignObject(int x, int y, int d1, int d2, int d3) {
		
	}
}
