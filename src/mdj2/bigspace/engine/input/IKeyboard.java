package mdj2.bigspace.engine.input;

import java.awt.event.KeyListener;

public interface IKeyboard {

	public boolean isKeyPressed(int keyCode);
	public boolean isKeyReleased(int keyCode);
	
	public boolean wasKeyPressed(int keyCode);
	public boolean wasKeyReleased(int keyCode);
	
	public void addKeyboardListener(KeyListener listener);
}