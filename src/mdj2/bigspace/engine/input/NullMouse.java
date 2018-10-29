package mdj2.bigspace.engine.input;

import java.awt.event.MouseListener;

public class NullMouse implements IMouse {

	@Override
	public boolean isLeftPressed() {
		System.err.println("[GameEngine_IMouse-ERROR] NullMouse isLeftPressed() Called");
		return false;
	}

	@Override
	public boolean isLeftReleased() {
		System.err.println("[GameEngine_IMouse-ERROR] NullMouse isLeftReleased() Called");
		return false;
	}

	@Override
	public boolean isRightPressed() {
		System.err.println("[GameEngine_IMouse-ERROR] NullMouse isRightPressed() Called");
		return false;
	}

	@Override
	public boolean isRightReleased() {
		System.err.println("[GameEngine_IMouse-ERROR] NullMouse isRightReleased() Called");
		return false;
	}

	@Override
	public boolean wasLeftPressed() {
		System.err.println("[GameEngine_IMouse-ERROR] NullMouse wasLeftPressed() Called");
		return false;
	}

	@Override
	public boolean wasLeftReleased() {
		System.err.println("[GameEngine_IMouse-ERROR] NullMouse wasLeftReleased() Called");
		return false;
	}

	@Override
	public boolean wasRightPressed() {
		System.err.println("[GameEngine_IMouse-ERROR] NullMouse wasRightPressed() Called");
		return false;
	}

	@Override
	public boolean wasRightReleased() {
		System.err.println("[GameEngine_IMouse-ERROR] NullMouse wasRightReleased() Called");
		return false;
	}

	@Override
	public int getMouseX() {
		System.err.println("[GameEngine_IMouse-ERROR] NullMouse getMouseX() Called");
		return -1;
	}

	@Override
	public int getMouseY() {
		System.err.println("[GameEngine_IMouse-ERROR] NullMouse getMouseY() Called");
		return -1;
	}

	@Override
	public void addMouseListener(MouseListener listener) {
		System.err.println("[GameEngine_IMouse-ERROR] NullMouse addMouseListener() Called");
	}

	

	
	
}
