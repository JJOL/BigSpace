package mdj2.bigspace.game.scenes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mdj2.bigspace.engine.GameScene;
import mdj2.bigspace.engine.graphics.GameWorld;
import mdj2.bigspace.engine.graphics.ImageEntity;
import mdj2.bigspace.engine.services.ServiceProvider;
import mdj2.bigspace.game.core.BigSpaceCore;
import mdj2.bigspace.game.levels.LevelWorld;
import mdj2.bigspace.game.levels.planets.Planet;
import mdj2.bigspace.game.levels.planets.PlanetInfo;
import mdj2.bigspace.game.resources.ResLoader;
import mdj2.bigspace.game.tasks.LevelLoaderTask;

public class GSPlay extends GameScene {

	private BigSpaceCore ctx;
	
	private PlanetInfo planetInfo;
	private LevelWorld levelWorld;
	private String lastPlanet;
	private int    lastLevel;
	
	public GSPlay(BigSpaceCore _ctx) {
		ctx = _ctx;
		
		// Load Last Planet and Level Played
		String savedPlanet = ServiceProvider.getStorage().getValue("last_planet");
		String savedLevel = ServiceProvider.getStorage().getValue("last_level");
		
		if (savedPlanet == null)
			lastPlanet = Planet.DEMO_PLANET;
		else
			lastPlanet = savedPlanet;
		
		if (savedLevel == null)
			lastLevel = 1;
		else
			lastLevel = Integer.parseInt(savedLevel);
		
		ServiceProvider.getTaskManager().runTask(new LevelLoaderTask(lastPlanet, lastLevel, this));
	}
	
	public void visitPlanet(String planetName) {
		ServiceProvider.getTaskManager().runTask(new LevelLoaderTask(planetName, 1, this));
		lastLevel = 1;
		lastPlanet = planetName;
	}
	
	
	@Override
	public void onSceneEnter() {
		System.out.println("Begin Play");
	}
	
	@Override
	public void sRender(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 640, 480);
		levelWorld.render(g);
	}

	@Override
	public void sUpdate() {
		levelWorld.update();
		
		if (levelWorld.isLevelOver()) {
			// Cargar Nivel
			ServiceProvider.getTaskManager().runTask(new LevelLoaderTask(lastPlanet, lastLevel+1, this));
		}
		
		
	}

	public void onLevelLoaded(PlanetInfo info, LevelWorld level) {
		planetInfo = info;
		levelWorld = level;
		ctx.onLevelLoaded(planetInfo); // For Enabling Play Button in Menu and showing
                                        // level image in menu
	}
}
