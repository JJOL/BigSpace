package mdj2.bigspace;

import mdj2.bigspace.engine.GameEngine;
import mdj2.bigspace.game.core.BigSpaceCore;

public class LauncherMain {

	public static void main(String[] args) {
		System.out.println("Launching Game...");
		GameEngine engine = new GameEngine(new BigSpaceCore());
		engine.setDebugMode(true);
		engine.start(); 
		/*UDPServer server = new UDPServer(25525);
		server.startServer();//*/
		/*UDPClient client = new UDPClient("127.0.0.1", 25525);
		client.connect();
		client.send("Hello World!");//*/
	}
}
