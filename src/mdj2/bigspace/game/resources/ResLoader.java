package mdj2.bigspace.game.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ResLoader {

	public static ResLoader go(String root) {
		if (root.length() > 0 && root.charAt(0) == '/') {
			return new ResLoader(root);
		}
		
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
	
	public AudioInputStream loadAudioStream() {
		AudioInputStream sample = null;
		System.out.println("Loading Audio from: "+ path);
		try {
			sample  = AudioSystem.getAudioInputStream(getClass().getResource(path));
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sample;
	}

	public String getPath() {
		return path;
	}
}
