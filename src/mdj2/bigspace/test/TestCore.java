package mdj2.bigspace.test;

import java.util.ArrayList;
import java.util.List;

import mdj2.bigspace.engine.EngineConfig;
import mdj2.bigspace.engine.GameCore;
import mdj2.bigspace.engine.GameScene;

public class TestCore extends GameCore {

	public final static int WWIDTH = 640, WHEIGHT = 480;
	
	
	// Context
	private boolean A = false;
	
	public EngineConfig getEngineConfig() {
		EngineConfig config = new EngineConfig();
		config.setGameName("Picazzo");
		config.setUPS(100);
		config.setWindowDimensions(WWIDTH, WHEIGHT);
		
		return config;
	}
	
	public List<GameScene> getGameScenes() {
		List<GameScene> scenes = new ArrayList<>();
		scenes.add(new TestPlayScene());
		scenes.add(new MenuScene(this));
		
		return scenes;
	}
	
	public void setParameterA() {
		A = !A;
		((PlayScene)ctx.getScenesList().get(1)).setA(A);
	}
	
}
