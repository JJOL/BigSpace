package mdj2.bigspace.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import mdj2.bigspace.engine.GameScene;
import mdj2.bigspace.engine.debug.CommandListener;
import mdj2.bigspace.engine.gui.ClickableAABB;
import mdj2.bigspace.engine.input.IKeyboard;
import mdj2.bigspace.engine.input.IMouse;
import mdj2.bigspace.engine.services.ServiceProvider;

public class MenuScene extends GameScene {

	
	Picazzo game;
	
	int x, y;
	
	ClickableAABB playBtn;
	ClickableAABB paramaterABtn;
	
	public MenuScene(Picazzo g) {
		super();
		game = g;
		 
		playBtn       = new ClickableAABB(30, 30, 110, 80, "Play");
		paramaterABtn = new ClickableAABB(120, 30, 200, 80, "Param A");
		
	}

	@Override
	public void sRender(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 640, 480);
		g.setColor(Color.YELLOW);
		g.drawString("Menu Screen", 30, 60);
		
		g.setColor(Color.WHITE);
		g.drawLine(x, y-10, x, y+10);
		g.drawLine(x-10, y, x+10, y);
		
		playBtn.eRender(g);
		paramaterABtn.eRender(g);
	}

	@Override 
	public void sUpdate() {
		
		IMouse mouse = ServiceProvider.getMouse();
		x = mouse.getMouseX();
		y = mouse.getMouseY();
		
		
		playBtn.eUpdate();
		if (playBtn.isClicked()) {
			System.out.println("Play Clicked");
			switchToScene(1);
		}
		
		paramaterABtn.eUpdate();
		if (paramaterABtn.isClicked()) {
			System.out.println("Param A CLicked");
			game.setParameterA();
		}
	}
	
}
