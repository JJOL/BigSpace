package mdj2.bigspace.test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import mdj2.bigspace.engine.gameobjects.GameObject;
import mdj2.bigspace.engine.graphics.GameWorld;
import mdj2.bigspace.engine.math.Vec2f;
import mdj2.bigspace.engine.services.ServiceProvider;

public class TestObject extends GameObject{

	
	public TestObject(GameWorld world) {
		super(world);
		mass = 1f;
		pos = new Vec2f(0,0);
		vel = new Vec2f(10, 0);
		acc = new Vec2f(-0.1f,0);
		
	}
	
	@Override
	public void render(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(pos.iX(), pos.iY(), 100, 100);
	}
	
	@Override
	public void update() {
		acc.add(-0.1f,0);
		if(ServiceProvider.getKeyboard().wasKeyPressed(KeyEvent.VK_SPACE)) {
			vel.add(10, 0);
		}
		
		if (vel.magnitude() > 10) {
			vel.scale(10 / vel.magnitude());
		}
	}
}
