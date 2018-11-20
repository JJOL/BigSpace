package mdj2.bigspace.game.entities.player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;

import mdj2.bigspace.engine.gameobjects.GameObject;
import mdj2.bigspace.engine.gameobjects.RectCollider;
import mdj2.bigspace.engine.graphics.AnimationSprite;
import mdj2.bigspace.engine.graphics.CameraObservable;
import mdj2.bigspace.engine.input.IKeyboard;
import mdj2.bigspace.engine.math.Vec2f;
import mdj2.bigspace.engine.services.ServiceProvider;
import mdj2.bigspace.engine.storage.IStorage;
import mdj2.bigspace.game.levels.GameCamera;

public class Player extends GameObject implements CameraObservable {

	private GameCamera camera;
	
	private int pWidth, pHeight;
	
	AnimationSprite sprite;
	
	private int jumpCount;
	
	// Inventory
	private int life = 300;
	private PlayerWeaponery weaponery;
	
	public Player() {
		super();
		sprite = new AnimationSprite(0,0, "enemy_1");
		sprite.setAnimSpd(5);
		mass = 3f;
		
		pWidth  = 32;
		pHeight = 64;
		
		camera = null;
		
		collider = new RectCollider(this, pWidth-5, pHeight-5);
		
		jumpCount = 0;
		
		weaponery = new PlayerWeaponery(this);
		weaponery.load();
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
			if (jumpCount <= 1) {
				vel.add(0, -5 );
				jumpCount++;
			}
		}
		
		
		
		if (keyboard.wasKeyPressed(KeyEvent.VK_E)) {
			weaponery.rollForwardWeapon();
		}
		
		if (keyboard.wasKeyPressed(KeyEvent.VK_Q)) {
			weaponery.rollBackWeapon();
		}
	}
	
	@Override
	public void update() {
		processInput();
		if (camera != null)
			camera.lookAt(pos);
		
		sprite.setX(pos.iX()-16);
		sprite.setY(pos.iY()-32);
		sprite.update();
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(pos.iX()-pWidth/2, pos.iY()-pHeight/2, pWidth, pHeight);	
		sprite.render(g);
	}

	@Override
	public void addCamera(GameCamera camera) {
		this.camera = camera;
	}
	
	@Override
	public void notifyMove() {
		
	}
	
	public void onGroundHit() {
		jumpCount = 0;
	}
}
