package mdj2.bigspace.engine.services;

import mdj2.bigspace.engine.debug.IConsoleCommander;
import mdj2.bigspace.engine.input.IKeyboard;
import mdj2.bigspace.engine.input.IMouse;
import mdj2.bigspace.engine.input.NullKeyboard;
import mdj2.bigspace.engine.input.NullMouse;

public class ServiceProvider {

	private static IConsoleCommander ccommander;
	private static IKeyboard keyboard;
	private static IMouse    mouse;
	
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
	
	
	public static void stopAllServices() {
		((IService)ccommander).stopService();
	}
}
