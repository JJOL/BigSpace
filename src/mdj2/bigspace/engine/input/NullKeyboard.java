package mdj2.bigspace.engine.input;

import java.awt.event.KeyListener;

import mdj2.bigspace.engine.services.IService;

public class NullKeyboard implements IKeyboard, IService {

	@Override
	public boolean isKeyPressed(int keyCode) {
		System.err.println("[GameEngine_IKeyboard-ERROR] NullKeyboard isKeyPressed() Called");
		return false;
	}

	@Override
	public boolean isKeyReleased(int keyCode) {
		System.err.println("[GameEngine_IKeyboard-ERROR] NullKeyboard isKeyReleased() Called");
		return false;
	}

	@Override
	public boolean wasKeyPressed(int keyCode) {
		System.err.println("[GameEngine_IKeyboard-ERROR] NullKeyboard wasKeyPressed() Called");
		return false;
	}

	@Override
	public boolean wasKeyReleased(int keyCode) {
		System.err.println("[GameEngine_IKeyboard-ERROR] NullKeyboard wasKeyReleased() Called");
		return false;
	}

	@Override
	public void addKeyboardListener(KeyListener listener) {
		System.err.println("[GameEngine_IKeyboard-ERROR] NullKeyboard addKeyboardListener() Called");
	}

	@Override
	public void startService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopService() {
		// TODO Auto-generated method stub
		
	}

}
