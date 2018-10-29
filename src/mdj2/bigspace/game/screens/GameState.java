package mdj2.bigspace.game.screens;

import java.awt.Graphics2D;

import mdj2.bigspace.game.core.BigSpaceCore;

public abstract class GameState {

	protected BigSpaceCore context;
	
	public GameState(BigSpaceCore context) {
		this.context = context;
	}
	
	
	public abstract void sRender(Graphics2D g);
	
	public abstract void sUpdate(double dt);
	
	
}
