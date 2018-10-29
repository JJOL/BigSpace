package mdj2.bigspace.engine.debug;

public class NullConsoleCommander implements IConsoleCommander{

	@Override
	public void addListener(String cmdName, CommandListener listener) {
		System.err.println("[GameEngine_IConsoleCommander-ERROR] NullConsoleCommaner addListener() Called");
	}

}
