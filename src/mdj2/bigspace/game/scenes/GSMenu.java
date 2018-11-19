package mdj2.bigspace.game.scenes;

import java.awt.Color;
import java.awt.Graphics2D;

import mdj2.bigspace.engine.GameScene;
import mdj2.bigspace.engine.graphics.ImageEntity;
import mdj2.bigspace.engine.gui.ClickableAABB;
import mdj2.bigspace.game.levels.planets.Planet;
import mdj2.bigspace.game.levels.planets.PlanetInfo;
import mdj2.bigspace.game.resources.ResLoader;

public class GSMenu extends GameScene{

	private ImageEntity space_img;
	private ImageEntity currPlanet_img;
	private ImageEntity ship_img;
	
	private boolean flying;
	private int planetIndex;
	
	// Input
	private ClickableAABB playBtn;
	private ClickableAABB configBtn;
	private ClickableAABB hubSelBtn;
	
	
	public GSMenu() {
		space_img = new ImageEntity(130, 0, 450, 260);
		
		space_img.load(ResLoader.go("images").at("ship_window").at("space_ship.jpg").getPath());
		
		ship_img       = new ImageEntity(0, 0, 640, 480);
		ship_img.load("images/menu_grid_.png");
		
		playBtn = new ClickableAABB(128, 40, 456, 160, "");
		hubSelBtn = new ClickableAABB(44, 166, 226, 267, "");
		configBtn = new ClickableAABB(382, 176, 600, 289, "");
		
		planetIndex = 0;
		currPlanet_img = new ImageEntity(130, 0, 450, 260);;
		flying = true;
	}
	
	@Override
	public void sRender(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 640, 480);
		if (flying)
			space_img.render(g);
		else
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
		switchToScene(1);
	}
	
	private void hubSelectorClicked() {
		System.out.println("Hub Selector Button Clicked!");
		if (planetIndex == 0) {
			planetIndex = 1;
			showPlanetArrived(Planet.loadPlanet("water"));
		} else {
			planetIndex = 0;
			showPlanetArrived(Planet.loadPlanet("fire"));
		}
		//switchToScene(3);
	}
	
	private void configClicked() {
		System.out.println("Config Button Clicked!");
	}
	
	// External Rsponse Methods
	
	public void flySpace() {
		flying = true;
	}
	
	public void showPlanetArrived(PlanetInfo planet) {
		currPlanet_img.load(planet.getShipImage());
		flying = false;
	}

}
