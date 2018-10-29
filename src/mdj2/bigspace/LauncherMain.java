package mdj2.bigspace;

import mdj2.bigspace.engine.GameEngine;
import mdj2.bigspace.game.core.BigSpaceCore;
import mdj2.bigspace.test.Picazzo;

public class LauncherMain {

	public static void main(String[] args) {
		System.out.println("Launching Game...");
		GameEngine engine = new GameEngine(new Picazzo());
		engine.setDebugMode(true);
		engine.start(); 
	}
}
