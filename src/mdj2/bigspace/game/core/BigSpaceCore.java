package mdj2.bigspace.game.core;

import java.awt.Graphics2D;
import java.util.List;

import mdj2.bigspace.engine.EngineConfig;
import mdj2.bigspace.engine.GameCore;
import mdj2.bigspace.engine.GameScene;

public class BigSpaceCore extends GameCore{

	/*public Game(DisplayGameLoop loop) {
		
	}*/
	
	public void prepareGame() {
		showLoadingScreen();
		
		//loadClasses();
		
		//loadServices();
		
		
	}
	
	private void showLoadingScreen() {
		
		//gameState = loadingState;
	}
	
	
	public void gameUpdate(double dt) {
		
	}
	
	public void gameRender(Graphics2D g) {
		
	}

	@Override
	public EngineConfig getEngineConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GameScene> getGameScenes() {
		// TODO Auto-generated method stub
		return null;
	}
}
