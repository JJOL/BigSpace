package mdj2.bigspace.game.core;

import java.util.ArrayList;
import java.util.List;

import mdj2.bigspace.engine.EngineConfig;
import mdj2.bigspace.engine.GameCore;
import mdj2.bigspace.engine.GameScene;
import mdj2.bigspace.game.levels.planets.PlanetInfo;
import mdj2.bigspace.game.scenes.GSMenu;
import mdj2.bigspace.game.scenes.GSPlay;

public class BigSpaceCore extends GameCore {

	@Override
	public EngineConfig getEngineConfig() {
		EngineConfig config = new EngineConfig();
		config.setGameName("BigSpace");
		config.setUPS(60);
		config.setWindowDimensions(640,  480);
		return config;
	}

	@Override
	public List<GameScene> getGameScenes() {
		List<GameScene> scenes = new ArrayList<>();
		scenes.add(new GSMenu());
		scenes.add(new GSPlay(this));
		return scenes;
	}

	public void onLevelLoaded(PlanetInfo planetInfo) {
		// TODO Auto-generated method stub
		
	}
}
