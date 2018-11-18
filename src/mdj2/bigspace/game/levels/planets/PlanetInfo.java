package mdj2.bigspace.game.levels.planets;

import java.awt.image.BufferedImage;

import mdj2.bigspace.game.levels.LevelLoader;
import mdj2.bigspace.game.resources.ResLoader;

public class PlanetInfo {

	private String planetName;
	private BufferedImage shipWindowImage;
	
	private BufferedImage levelBackground;
	
	private LevelLoader levelLoader;
	
	public PlanetInfo(String planetName, String fShipImg, String fBackImg, LevelLoader loader) {
		this.planetName = planetName;
		this.levelLoader = loader;
		//shipWindowImage = ResLoader.go("images").at("ship_window").at(planetName+"_ship.jpg").loadBufferedImage();
		//levelBackground = ResLoader.go("images").at("backgrounds").at(planetName+"_back.jpg").loadBufferedImage();
	}
	
	public String getPlanetName() {
		return planetName;
	}
	
	public BufferedImage getShipImage() {
		return shipWindowImage;
	}
	
	public BufferedImage getLevelBackground() {
		return levelBackground;
	}
	
	public LevelLoader getLevelLoader() {
		return levelLoader;
	}
}
