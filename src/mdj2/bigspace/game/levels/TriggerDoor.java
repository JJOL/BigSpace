package mdj2.bigspace.game.levels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import mdj2.bigspace.engine.gameobjects.GameObject;
import mdj2.bigspace.engine.gameobjects.RectCollider;
import mdj2.bigspace.game.entities.player.Player;
import mdj2.bigspace.game.resources.ResLoader;

public class TriggerDoor extends GameObject {

	private boolean active = false;
	BufferedImage door;
	
	public TriggerDoor() {
		collider = new RectCollider(this, 32, 64);
		door = ResLoader.go("textures").at("door.jpg").loadBufferedImage();
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(pos.iX(), pos.iY()-32, 32, 64);
		g.drawImage(door, pos.iX(), pos.iY()-32, 32, 64, null);
		super.render(g);
	}
	
	public boolean activated() {
		return active;
	}
	
	@Override
	public void onCollision(GameObject other) {
		if (other instanceof Player)
			active = true;
	}

}
