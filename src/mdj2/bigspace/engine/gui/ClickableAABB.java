package mdj2.bigspace.engine.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import mdj2.bigspace.engine.input.IMouse;
import mdj2.bigspace.engine.services.ServiceProvider;

public class ClickableAABB {

	int x1, y1, x2, y2;
	String str;
	
	boolean isPressed;
	
	int c = 0;
	
	public ClickableAABB(int x1, int y1, int x2, int y2, String str) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.str = str;
	}
	
	public void eRender(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(x1, y1, x2-x1, y2-y1);
		
		g.setColor(Color.BLACK);
		g.drawString(str, x1+10, y1+30);
		
		isPressed = false;
	}
	
	public void eUpdate() {
		isPressed = false;
		IMouse mouse = ServiceProvider.getMouse();
		int mx = mouse.getMouseX(), 
			my = mouse.getMouseY();
		
		
		if (mouse.wasLeftPressed() && 
				(mx >= x1 && mx <= x2 && my >= y1 && my <= y2)) {
			isPressed = true;
		}
	}
	
	public boolean isPressed() {
		return false;
	}
	
	public boolean isClicked() {
		return isPressed;
	}
}
