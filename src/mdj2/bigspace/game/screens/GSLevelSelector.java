package mdj2.bigspace.game.screens;

import java.awt.Color;
import java.awt.Graphics2D;

import mdj2.bigspace.game.core.BigSpaceCore;

public class GSLevelSelector extends GameState {

	public GSLevelSelector(BigSpaceCore context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sRender(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setColor(Color.BLACK);
		g.drawString("LevelSelector", 150, 150);
	}

	@Override
	public void sUpdate(double dt) {
		// TODO Auto-generated method stub
		
	}

}
