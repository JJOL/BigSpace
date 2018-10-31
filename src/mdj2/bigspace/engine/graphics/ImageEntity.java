package mdj2.bigspace.engine.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageEntity {

	private int x, y;
	private int width, height;
	private BufferedImage img;
	
	public ImageEntity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void load(String path)  {
		try {
			img = ImageIO.read(getClass().getResource("/images/"+path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render(Graphics2D g) {
		g.drawImage(img, x, y, x+width, y+height, 0, 0, img.getWidth(), img.getHeight(), null);
	}
	
	public void setX(int _x) {
		x = _x;
	}
	
	public void setY(int _y) {
		y = _y;
	}
	
}
