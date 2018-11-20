package mdj2.bigspace.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import mdj2.bigspace.engine.gameobjects.GameObject;
import mdj2.bigspace.engine.gameobjects.RectCollider;
import mdj2.bigspace.engine.graphics.CameraObservable;
import mdj2.bigspace.engine.input.IKeyboard;
import mdj2.bigspace.engine.math.Vec2f;
import mdj2.bigspace.engine.services.ServiceProvider;
import mdj2.bigspace.game.levels.GameCamera;

public class Player extends GameObject implements CameraObservable {

	private GameCamera camera;
	
	private int pWidth, pHeight;
	
	
	
	public Player() {
		super();
		mass = 3f;
		
		pWidth  = 32;
		pHeight = 64;
		
		camera = null;
		
		collider = new RectCollider(this, pWidth, pHeight);
	}
	
	private void processInput() {
		IKeyboard keyboard = ServiceProvider.getKeyboard();
		vel = new Vec2f(0f, vel.y);
		
		if (keyboard.isKeyPressed(KeyEvent.VK_A)) {
			vel.add(-5, 0);
			//acc.add(-0.6f, 0);
		}
		
		if (keyboard.isKeyPressed(KeyEvent.VK_D)) {
			vel.add(5, 0);
			//acc.add(0.6f, 0);
		}
		
		if (keyboard.wasKeyPressed(KeyEvent.VK_SPACE)) {
			vel.add(0, -5 );
		}
	}
	
	@Override
	public void update() {
		processInput();
		if (camera != null)
			camera.lookAt(pos);
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(pos.iX()-pWidth/2, pos.iY()-pHeight/2, pWidth, pHeight);
	}

	@Override
	public void addCamera(GameCamera camera) {
		this.camera = camera;
	}
	
	@Override
	public void notifyMove() {
		
	}
}
