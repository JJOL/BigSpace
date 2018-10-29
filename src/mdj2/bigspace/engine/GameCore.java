package mdj2.bigspace.engine;

import java.util.List;

public abstract class GameCore {

	protected GameContext ctx;
	
	public GameCore() {
		
	}
	
	public abstract EngineConfig getEngineConfig();
	
	public abstract List<GameScene> getGameScenes();
	
	public void setContext(GameContext ctx) {
		this.ctx = ctx;
	}
	
}
