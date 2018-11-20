package mdj2.bigspace.game.scenes;

import java.awt.Color;
import java.awt.Graphics2D;

import mdj2.bigspace.engine.GameScene;
import mdj2.bigspace.engine.graphics.ImageEntity;
import mdj2.bigspace.engine.gui.ClickableAABB;

public class GSLevelSelector extends GameScene{
	ImageEntity planet_fuego_img;
	ImageEntity planet_fuego_sel;
	ImageEntity planet_agua_sel;
	ImageEntity planet_aire_sel;
	ImageEntity planet_agua_img;
	ImageEntity planet_aire_img;
	ImageEntity planet_tierra_img;
	ImageEntity planet_tierra_sel;
	ImageEntity space_img;
	ImageEntity info_f;	
	ImageEntity info_ag;
	ImageEntity info_ai;
	ImageEntity info_t;
	ImageEntity info_sel;
	ImageEntity currPlanet_img;

	
	private ClickableAABB plF;
	private ClickableAABB plAg;
	private ClickableAABB plAi;
	private ClickableAABB plT;
	
	public GSLevelSelector() {
		space_img = new ImageEntity(0,0,640,480);
		space_img.load("space.jpg");
		info_f = new ImageEntity(120,150, 100,120);
		info_ag = new ImageEntity(350,70, 100,120);
		info_ai = new ImageEntity(350,350, 100,120);
		info_t = new ImageEntity(500,150, 100,120);
		info_f.load("info_pl.png");
		info_ag.load("info_pl.png");
		info_ai.load("info_pl.png");
		info_t.load("info_pl.png");
		
		planet_fuego_sel = new ImageEntity(70,150,90,90);
		planet_fuego_img = new ImageEntity(70,150,90,90);
		planet_agua_sel = new ImageEntity(300,70,90,90);
		planet_agua_img = new ImageEntity(300,70,90,90);
		planet_aire_sel = new ImageEntity(300,350,90,90);
		planet_aire_img = new ImageEntity(300,350,90,90);
		planet_tierra_sel = new ImageEntity(550,150,90,90);
		planet_tierra_img = new ImageEntity(550,150,90,90);
		
		planet_fuego_sel.load("planeta_fuego_selec.png");
		planet_fuego_img.load("planeta_fuego.png");
		planet_agua_sel.load("planeta_agua_sel.png");
		planet_agua_img.load("planeta_agua.png");
		planet_aire_sel.load("planeta_aire_sel.png");
		planet_aire_img.load("planeta_aire.png");
		planet_tierra_sel.load("planeta_tierra_selec.png");
		planet_tierra_img.load("planeta_tierra.png");
		
		plF = new ClickableAABB(70, 150, 140, 220, "");
		plAg = new ClickableAABB(300, 70, 370, 140, "");
		plAi = new ClickableAABB(300, 350, 370, 420, "");
		plT = new ClickableAABB(550, 150, 620, 220, "");
		
		currPlanet_img = planet_fuego_img;
		info_sel =planet_aire_img;
	}
	
	@Override
	public void sRender(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 640, 480);
		planet_fuego_sel.render(g);
		planet_tierra_sel.render(g);
		planet_agua_sel.render(g);
		planet_aire_sel.render(g);
		info_f.render(g);
		info_ag.render(g);
		info_ai.render(g);
		info_t.render(g);
		space_img.render(g);
		//info_f.render(g);
		planet_fuego_img.render(g);
		planet_agua_img.render(g);
		planet_aire_img.render(g);
		planet_tierra_img.render(g);
		currPlanet_img.render(g);
		info_sel.render(g);
	}

	@Override
	public void sUpdate() {
		// TODO Auto-generated method stub
		plF.eUpdate();
		plAg.eUpdate();
		plAi.eUpdate();
		plT.eUpdate();
		if (plF.isClicked()) {
			plFClicked();
		}
		if (plAg.isClicked()) {
			plAgClicked();
		}
		if (plAi.isClicked()) {
			plAiClicked();
		}
		if (plT.isClicked()) {
			plTClicked();
		}
	}
	
	private void plFClicked() {
		System.out.println("Fire Planet Selected!");
		currPlanet_img = planet_fuego_sel;
		info_sel = info_f;
	}
	private void plAgClicked() {
		System.out.println("Aqua Planet Selected!");
		currPlanet_img = planet_agua_sel;
		info_sel = info_ag;
	}
	private void plAiClicked() {
		System.out.println("Air Planet Selected!");
		currPlanet_img = planet_aire_sel;
		info_sel = info_ai;
	}
	private void plTClicked() {
		System.out.println("Earth Planet Selected!");
		currPlanet_img = planet_tierra_sel;
		info_sel = info_t;
	}

}