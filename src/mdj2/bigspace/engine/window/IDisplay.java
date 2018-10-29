package mdj2.bigspace.engine.window;

import mdj2.bigspace.engine.input.IKeyboard;
import mdj2.bigspace.engine.input.IMouse;

public interface IDisplay {

	public void showWindow();
	
	public void hideWindow();
	
	public void disposeWindow();
	
	public void renderWindow();
}
