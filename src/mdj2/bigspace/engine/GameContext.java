package mdj2.bigspace.engine;

import java.util.List;

public class GameContext {

	private List<GameScene> scenes;
	
	public GameContext(List<GameScene> scenes) {
		this.scenes = scenes;
	}
	
	public List<GameScene> getScenesList() {
		return scenes;
	}
	
}
