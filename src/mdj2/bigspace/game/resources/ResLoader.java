package mdj2.bigspace.game.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResLoader {

	public static ResLoader go(String root) {
		return new ResLoader("/"+root);
	}
	
	private String path = "";
	
	public ResLoader(String root) {
		path = root;
	}
	
	public ResLoader at(String root) {
		return new ResLoader(path + "/" + root);
	}
	
	public BufferedImage loadBufferedImage() {
		BufferedImage img = null;
		System.out.println("Loading impage from " + path);
		try {
			img = ImageIO.read(getClass().getResource(path));
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (img == null) {
				//img = DEFAULT_IMG;
			}
		}
		
		return img;
	}
}
