package mdj2.bigspace.engine.debug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import mdj2.bigspace.engine.services.IService;

public class TestConsoleCommander implements IService, IConsoleCommander, Runnable{

	private boolean running;
	
	public static final String ALL_COMMANDS = "--ALL--";
	
	private List<CommandListener> listeners;
	private List<String>          stimulus;
	private BufferedReader consoleReader;
	
	public TestConsoleCommander () {
		listeners = new ArrayList<>();
		stimulus  = new ArrayList<>();
		consoleReader = new BufferedReader(new InputStreamReader(System.in));
		running = false;
	}

	@Override
	public void addListener(String cmdName, CommandListener listener) {
		listeners.add(listener);
		stimulus.add(cmdName);
	}

	@Override
	public void run() {
		while (running) {
			String cmdLine = "";
			
			try {
				cmdLine = consoleReader.readLine();
				System.out.println("[ConsoleCommander] Command Read!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String cmdParts[] = cmdLine.split(" ");
			String cmdName, cmdArgs[];
			
			System.out.println("Command Parts Length" + cmdParts.length);
			
			if (cmdParts.length > 0 && cmdParts[0] != null && !cmdParts[0].trim().isEmpty()) {
				
				cmdName = cmdParts[0];
				cmdArgs = new String[cmdParts.length-1];
				
				for (int i = 1; i < cmdParts.length - 1; i++) {
					cmdArgs[i] = cmdParts[i];
				}
				System.out.println("Processing command " + cmdName);
				notifyListeners(cmdName, cmdArgs);
			}
		}
		
		System.out.println("[ConsoleCommanderService Stopped]");
	}
	
	public void notifyListeners(String cmdName, String cmdArgs[]) {
		int size = listeners.size();
		
		for (int i = 0; i < size; i++) {
			if (stimulus.get(i).equalsIgnoreCase(cmdName) || stimulus.get(i).equalsIgnoreCase(ALL_COMMANDS)) {
				System.out.println("Sending command...");
				listeners.get(i).onCommand(cmdName, cmdArgs);
			}
		}
	}

	@Override
	public void startService() {
		if (!running) {
			System.out.println("[Starting ConsoleCommanderService]");
			running = true;
			Executors.newSingleThreadExecutor().execute(this);
		}
	}

	@Override
	public synchronized void stopService() {
		running = false;
	}
}
