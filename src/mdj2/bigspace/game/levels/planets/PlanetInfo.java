package mdj2.bigspace.game.levels.planets;

import java.awt.image.BufferedImage;

import mdj2.bigspace.game.levels.LevelLoader;
import mdj2.bigspace.game.resources.ResLoader;

public class PlanetInfo {

	private String planetName;
	private String shipWindowImagePath;
	private String levelBackgroundPath;
	
	private LevelLoader levelLoader;
	
	public PlanetInfo(String planetName, String fShipImg, String fBackImg, LevelLoader loader) {
		this.planetName = planetName;
		this.levelLoader = loader;
		shipWindowImagePath = ResLoader.go("images").at("ship_window").at(fShipImg).getPath();
		levelBackgroundPath = ResLoader.go("images").at("backgrounds").at(planetName+"_back.jpg").getPath();
	}
	
	public String getPlanetName() {
		return planetName;
	}
	
	public String getShipImage() {
		
		return shipWindowImagePath;
	}
	
	public String getLevelBackground() {
		return levelBackgroundPath;
	}
	
	public LevelLoader getLevelLoader() {
		return levelLoader;
	}
}
