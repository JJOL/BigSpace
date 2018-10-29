package mdj2.bigspace.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import mdj2.bigspace.engine.input.IKeyboard;
import mdj2.bigspace.engine.services.ServiceProvider;

public class Player  {
	
	private int x, y, width, height;
	
	
	public Player(int x, int y) {
		width = 32;
		height = 64;
	}
	
	public void gRender(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(x-width/2, y-height/2, width, height);
	}
	
	public void gUpdate(double dt) {
		//IKeyboard keyboard = ServiceProvider.getKeyboard();
		
		/*if (keyboard.isKeyPressed(KeyEvent.VK_A)) {
			//hspd -= 5;
		}
		
		if (keyboard.isKeyPressed(KeyEvent.VK_D)) {
			//hspd += 5;
		}
		
		//x += hspd;
	*/	
	}

}
