package mdj2.bigspace.game.tasks;

import mdj2.bigspace.game.levels.LevelLoader;
import mdj2.bigspace.game.levels.LevelWorld;
import mdj2.bigspace.game.levels.planets.Planet;
import mdj2.bigspace.game.levels.planets.PlanetInfo;
import mdj2.bigspace.game.scenes.GSPlay;

public class LevelLoaderTask implements Runnable {
	
	private String planetName;
	private int levelId;
	
	private LevelWorld levelWorld;
	private PlanetInfo planetInfo;
	
	private GSPlay playScene;
	
	public LevelLoaderTask(String _planetName, int _levelId, GSPlay _playScene) {
		planetName = _planetName;
		levelId    = _levelId;
		playScene  = _playScene;
	}
	
	public void run() {
		planetInfo = Planet.loadPlanet(planetName);
		LevelLoader levelLoader = planetInfo.getLevelLoader();		
		
		// Handle Previously Loaded Worlds
		// Do we delete players, levels and scores??
		
		levelWorld = levelLoader.loadWorld(levelId);
		
		playScene.onLevelLoaded(planetInfo, levelWorld);
	}
	
	public LevelWorld getLevelWorld() {
		return levelWorld;
	}
	
	public PlanetInfo getPlanetInfo() {
		return planetInfo;
	}
}
