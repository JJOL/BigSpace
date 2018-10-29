package mdj2.bigspace.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import mdj2.bigspace.engine.GameScene;
import mdj2.bigspace.engine.services.ServiceProvider;

public class PlayScene extends GameScene {
	
	int count = 0;
	int secs = 0;
	Picazzo game;
	
	
	boolean A;
	
	public PlayScene(Picazzo g) {
		super();
		game = g;
		A = false;
	}

	@Override
	public void sRender(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 640, 480);
		g.setColor(Color.WHITE);
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 24));
		g.drawString("Play Time: " + secs + "s", 30, 30);
	}

	@Override
	public void sUpdate() {
		count++;
		if (count % 60 == 0) {
			secs = A ? secs-1 : secs+1;
		}
		
		if (ServiceProvider.getKeyboard().wasKeyPressed(KeyEvent.VK_SPACE)) {
			switchToScene(0);
		}
	}
	public void setA(boolean A) {
		this.A = A;
	}
	
}
