package mdj2.bigspace.game.scenes;

import java.awt.Color;
import java.awt.Graphics2D;

import mdj2.bigspace.engine.GameScene;
import mdj2.bigspace.engine.graphics.ImageEntity;
import mdj2.bigspace.engine.gui.ClickableAABB;

public class GSMenu extends GameScene{

	ImageEntity fire_planet_img;
	ImageEntity water_planet_img;
	ImageEntity earth_planet_img;
	ImageEntity air_planet_img;
	
	ImageEntity currPlanet_img;
	ImageEntity ship_img;
	
	private int planetIndex;
	
	// Input
	private ClickableAABB playBtn;
	private ClickableAABB configBtn;
	private ClickableAABB hubSelBtn;
	
	
	public GSMenu() {
		fire_planet_img = new ImageEntity(130, 0, 450, 260);
		water_planet_img = new ImageEntity(130, 0, 450, 260);
		earth_planet_img = new ImageEntity(130, 0, 450, 260);
		air_planet_img = new ImageEntity(140, 0, 450, 260);
		
		fire_planet_img.load("fire_planet_scene.jpg");
		water_planet_img.load("water_planet_scene.jpg");
		
		ship_img       = new ImageEntity(0, 0, 640, 480);
		ship_img.load("menu_grid_.png");
		
		playBtn = new ClickableAABB(128, 40, 456, 160, "");
		hubSelBtn = new ClickableAABB(44, 166, 226, 267, "");
		configBtn = new ClickableAABB(382, 176, 600, 289, "");
		
		planetIndex = 0;
		currPlanet_img = fire_planet_img;
	}
	
	@Override
	public void sRender(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 640, 480);
		currPlanet_img.render(g);
		ship_img.render(g);
	}

	@Override
	public void sUpdate() {	
		playBtn.eUpdate();
		configBtn.eUpdate();
		hubSelBtn.eUpdate();
		
		if (playBtn.isClicked()) {
			playClicked();
		}
		if (configBtn.isClicked()) {
			configClicked();
		}
		if (hubSelBtn.isClicked()) {
			hubSelectorClicked();
		}
	}
	
	private void playClicked() {
		if (planetIndex == 0) {
			planetIndex = 1;
			currPlanet_img = water_planet_img;
		} else {
			planetIndex = 0;
			currPlanet_img = fire_planet_img;
		}
		
		switchToScene(1);
	}
	
	private void hubSelectorClicked() {
		System.out.println("Hub Selector Button Clicked!");
		if (planetIndex == 0) {
			planetIndex = 1;
			currPlanet_img = water_planet_img;
		} else {
			planetIndex = 0;
			currPlanet_img = fire_planet_img;
		}
	}
	
	private void configClicked() {
		System.out.println("Config Button Clicked!");
	}

}
