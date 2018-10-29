package mdj2.bigspace.engine;

import java.awt.Graphics2D;

public abstract class GameScene {
	
	private GameEngine engine;
	
	public void bind(GameEngine engine) {
		this.engine = engine;
	}

	public abstract void sRender(Graphics2D g);
	
	public abstract void sUpdate();
	
	public void switchToScene(int sceneIndex) {
		engine.switchToScene(sceneIndex);
	}
	
	public void onSceneEnter() {
		
	}
	
	public void onSceneLeave() {
		
	}
	
}
