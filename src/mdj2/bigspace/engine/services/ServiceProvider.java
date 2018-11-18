package mdj2.bigspace.engine.services;

import mdj2.bigspace.engine.debug.IConsoleCommander;
import mdj2.bigspace.engine.input.IKeyboard;
import mdj2.bigspace.engine.input.IMouse;
import mdj2.bigspace.engine.input.NullKeyboard;
import mdj2.bigspace.engine.input.NullMouse;
import mdj2.bigspace.engine.storage.IStorage;
import mdj2.bigspace.engine.threading.ITaskManager;
import mdj2.bigspace.engine.threading.NullTaskManager;

public class ServiceProvider {

	private static IConsoleCommander ccommander;
	private static IKeyboard keyboard;
	private static IMouse    mouse;
	private static IStorage storage;
	private static ITaskManager taskManager;
	
	public static void setConsoleCommander(IConsoleCommander _ccommander) {
		ccommander = _ccommander;
	}
	
	public static IConsoleCommander getConsoleCommander() {
		return ccommander;
	}
	
	public static void setKeyboard(IKeyboard _keyboard) {
		keyboard = _keyboard;
	}
	
	public static IKeyboard getKeyboard() {
		if (keyboard == null)
			return new NullKeyboard();
		return keyboard;
	}
	
	public static void setMouse(IMouse _mouse) {
		mouse = _mouse;
	}
	
	public static IMouse getMouse() {
		if (mouse == null)
			return new NullMouse();
		return mouse;
	}
	
	public static void setStorage(IStorage _storage) {
		storage = _storage;
	}
	
	public static IStorage getStorage() {
		return storage;
	}
	
	public static void setTaskManager(ITaskManager _taskManager) {
		taskManager = _taskManager;
	}
	
	public static ITaskManager getTaskManager() {
		if (taskManager == null)
			return new NullTaskManager();
		
		return taskManager;
	}
	
	public static void stopAllServices() {
		((IService)ccommander).stopService();
	}
}
