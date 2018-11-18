package mdj2.bigspace.engine;

import java.awt.Graphics2D;
import java.util.List;

import mdj2.bigspace.engine.debug.TestConsoleCommander;
import mdj2.bigspace.engine.input.SwingKeyboard;
import mdj2.bigspace.engine.input.SwingMouse;
import mdj2.bigspace.engine.services.IService;
import mdj2.bigspace.engine.services.ServiceProvider;
import mdj2.bigspace.engine.storage.DummyStorage;
import mdj2.bigspace.engine.threading.ExecutorTaskManager;
import mdj2.bigspace.engine.window.CanvasDisplay;
import mdj2.bigspace.engine.window.IDisplay;

public class GameEngine implements Runnable {

	// Window and Inputs
	private IDisplay wDisplay;
	
	private GameCore gameCore;
	private EngineConfig engineConfig;
	
	private List<GameScene> gameScenes;
	private GameScene currentScene;
	
	// Game Loop
	private Thread gameThread;
	boolean running = false;
	
	
	
	public GameEngine(GameCore gameCore) {
		this.gameCore = gameCore;
		this.engineConfig = gameCore.getEngineConfig();
		checkEngineConfig(engineConfig);
		
		SwingKeyboard keyboard = new SwingKeyboard();
		SwingMouse    mouse    = new SwingMouse();
		wDisplay = new CanvasDisplay(this, 
				engineConfig.getWindowDimensions(), engineConfig.getGameName(),
				keyboard, mouse);
		
		keyboard.startService();
		mouse.startService();
		ServiceProvider.setKeyboard(keyboard);
		ServiceProvider.setMouse(mouse);
		
		ServiceProvider.setStorage(new DummyStorage());
		ServiceProvider.setTaskManager(new ExecutorTaskManager());
		((IService)ServiceProvider.getTaskManager()).startService();
	}
	
	/*
	 * checkEngineConfig(EngineConfig config)
	 * returns true if the Configuration is error free.
	 * If it's not, it returns false and also prints into the Log all the Errors found in the config
	 * */
	private boolean checkEngineConfig(EngineConfig config) {
		List<Integer>errors = config.getErrors();
	
		for (int error : errors) {
			String errMsg = config.getErrorMessage(error);
			System.err.println("[GameEngine-Error]: " + errMsg);
		}
		
		return errors.isEmpty();
	}
	
	
	public void setDebugMode(boolean mode) {
		
	}
	
	
	
	
	public void start() {
		//ServiceProvider.setConsoleCommander(new TestConsoleCommander());
		// Create the thread if it doesn't exist and start it
		
		TestConsoleCommander commander = new TestConsoleCommander();
		commander.startService();
		ServiceProvider.setConsoleCommander(commander);
		
		wDisplay.showWindow();
		if (gameThread == null && !running) {
			running = true;
			gameThread = new Thread(this);
			
			gameScenes  = gameCore.getGameScenes();
			for (GameScene scene : gameScenes)
				scene.bind(this);
			currentScene = gameScenes.get(0);
			
			GameContext ctx = new GameContext(gameScenes);
			gameCore.setContext(ctx);
			
			
			
			gameThread.start();
		}
		
		
	}
	
	public void stop() {
		//ServiceProvider.stopAllServices();
	}
	
	// Game Loop
	public void run() {
		//final long period = 10 * 1000000; // Period T = 10ms = 10*1e6 ns
		final long period = 1_000_000_000 / engineConfig.getUPS(); // Period T = 10ms = 10*1e6 ns
		final int DELAYS_PER_YIELD = 16;
		
		long sleepTime = 0L;
		long overSleep = 0L;
		long previousTime, currentTime, deltaTime;
		
		long timer = System.currentTimeMillis();
		int fpsCount = 0, upsCount = 0;
		
		int noDelays = 0;
		
		previousTime = System.nanoTime(); 
	
		
		while (running) {
			gUpdate();
			upsCount++;
			windowRender();
			fpsCount++;
			
			currentTime = System.nanoTime();
			deltaTime   = currentTime - previousTime;
			sleepTime   = period - deltaTime - overSleep;
			
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime / 1000000L);
				} catch(InterruptedException e) {
					System.out.println("GameLoop Sleep Exception!");
				}
				
				overSleep = (System.nanoTime() - currentTime) - sleepTime;
			} else {
				overSleep = 0;
				
				if (++noDelays >= DELAYS_PER_YIELD) {
					noDelays = 0;
					Thread.yield();
				}
			}
			
			
			if (System.currentTimeMillis() >= (timer + 1000)) {
				//setTitle("FPS: " + fpsCount + " - UPS: " + upsCount + " -- Canvas Game");
				timer += 1000;
				fpsCount = 0;
				upsCount = 0;
			}
			
			
			previousTime = System.nanoTime();
		}
		
		wDisplay.disposeWindow();
		System.exit(0);
	}
	
	public void windowRender() {
		wDisplay.renderWindow();
	}
	
	public void gRender(Graphics2D g) {
		currentScene.sRender(g);
	}
	
	public void gUpdate() {
		currentScene.sUpdate();
		((SwingKeyboard)ServiceProvider.getKeyboard()).updatePrevKeyStates();
		((SwingMouse)ServiceProvider.getMouse()).updatePrevBtnsStates();
	}
	
	public void switchToScene(int sceneIndex) {
		if (sceneIndex < 0 || sceneIndex >= gameScenes.size()) {
			System.err.println("[GameEngine-Error] Invalid Scene Index Switch Request! 'sceneIndex'=" + sceneIndex);
			return;
		}
		
		currentScene.onSceneLeave();
		currentScene = gameScenes.get(sceneIndex);
		currentScene.onSceneEnter();
	}
	
	public void onDisplayClosed() {
		running = false;
	}
}
