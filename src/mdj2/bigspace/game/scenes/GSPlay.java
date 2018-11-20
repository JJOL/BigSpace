package mdj2.bigspace.game.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import mdj2.bigspace.engine.GameScene;
import mdj2.bigspace.engine.graphics.AnimationSprite;
import mdj2.bigspace.engine.services.ServiceProvider;
import mdj2.bigspace.game.AudioPlayer;
import mdj2.bigspace.game.core.BigSpaceCore;
import mdj2.bigspace.game.levels.LevelWorld;
import mdj2.bigspace.game.levels.planets.Planet;
import mdj2.bigspace.game.levels.planets.PlanetInfo;
import mdj2.bigspace.game.tasks.LevelLoaderTask;

public class GSPlay extends GameScene {

	enum SceneState {
		PLAYING, TRANSITION_ENTER, TRANSITION_LEAVE, LOADING
	}
	private SceneState sceneState;
	
	
	private BigSpaceCore ctx;
	
	private PlanetInfo planetInfo;
	private LevelWorld levelWorld;
	private String lastPlanet;
	private int    lastLevel;
	
	// Animation Transitions
	AnimationSprite transitionEnterAnim;
	AnimationSprite transitionLeaveAnim;
	
	int passedLevels;
	
	public GSPlay(BigSpaceCore _ctx) {
		ctx = _ctx;
		
		transitionEnterAnim = new AnimationSprite(0, 0, "transition_in");
		transitionLeaveAnim = new AnimationSprite(0, 0, "transition_out");
		
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
		sceneState = SceneState.LOADING;
		
		AudioPlayer.get().playSound("ambient");
	}
	
	public void visitPlanet(String planetName) {
		//TODO: Handle World Leave, Saves, Etc?
		ServiceProvider.getTaskManager().runTask(new LevelLoaderTask(planetName, 1, this));
		lastLevel = 1;
		lastPlanet = planetName;
		sceneState = SceneState.LOADING;		
	}
	
	
	@Override
	public void onSceneEnter() {
		System.out.println("Begin Play");
		sceneState = SceneState.PLAYING;
		levelWorld.begin();
		passedLevels = 0;
		//
	}
	
	@Override
	public void sRender(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 640, 480);
		
		switch (sceneState) {
		case PLAYING:
			levelWorld.render(g);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Times New Roman", Font.BOLD, 24));
			g.drawString("Time Left: " + levelWorld.getTime(), 10, 25);
			
			g.drawString("Levels Passed: " + passedLevels, 10, 40);
			// TODO: hud.render(g);
			break;
		case TRANSITION_ENTER:
			transitionEnterAnim.render(g);
			break;
		case TRANSITION_LEAVE:
			transitionLeaveAnim.render(g);
			break;
		case LOADING:
		default:
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 640, 480);
		}
		
		//g.translate(0, 0);
		
	}

	@Override
	public void sUpdate() {
		
		switch (sceneState) {
		case PLAYING:
			levelWorld.update();
			
			if (levelWorld.isLevelOver()) {
				// Cargar Nivel
				if (levelWorld.getNextLevel() == 1)
					lastLevel = lastLevel + 1;
				else
					lastLevel = lastLevel - 1;
				
				passedLevels++;
				
				ServiceProvider.getTaskManager().runTask(new LevelLoaderTask(lastPlanet, lastLevel, this));
					
				sceneState = SceneState.TRANSITION_ENTER;
				transitionEnterAnim.reset();
			}
			
			if (levelWorld.getTime() <= 0) {
				visitPlanet(ServiceProvider.getStorage().getValue("last_planet"));
				switchToScene(0);
			}
			
			break;
		case TRANSITION_ENTER:
			transitionEnterAnim.update();
			if (transitionEnterAnim.hasReachedEnd())
				sceneState = SceneState.LOADING;
			break;
		case TRANSITION_LEAVE:
			sceneState = SceneState.PLAYING;
			levelWorld.begin();/*
			transitionLeaveAnim.update();
			if (transitionLeaveAnim.hasReachedEnd()) {
				sceneState = SceneState.PLAYING;
				levelWorld.begin();
			}*/
			break;
		case LOADING:
			//TODO: Handle Timeouts??
			default:
		}
		
	}

	public void onLevelLoaded(PlanetInfo info, LevelWorld level) {
		planetInfo = info;
		levelWorld = level;
		ctx.onLevelLoaded(planetInfo); // For Enabling Play Button in Menu and showing
                                        // level image in menu
		
		// Maquina de Estado
		sceneState = SceneState.TRANSITION_LEAVE;
		transitionLeaveAnim.reset();
	}
}
