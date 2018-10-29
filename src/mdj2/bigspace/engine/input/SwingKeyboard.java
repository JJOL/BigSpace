package mdj2.bigspace.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import mdj2.bigspace.engine.services.IService;

public class SwingKeyboard implements IService, IKeyboard, KeyListener{

	// Amount of Keys
	private final int TOTAL_KEYS;
	// Table for Holding the current states of the keys
	private boolean keyStates[];
	//Table for Holding the previous states of the keys
	private boolean prevKeyStates[];
	
	// Control
	private boolean active;
	
	// Listeners
	private List<KeyListener> listeners;
	
	public SwingKeyboard() {
		TOTAL_KEYS    = 1024;
		keyStates     = new boolean[TOTAL_KEYS];
		prevKeyStates = new boolean[TOTAL_KEYS];
		
		listeners = new ArrayList<>();
		
		active = false;
	}
	
	private void resetStates() {
		for (int i = 0; i < TOTAL_KEYS; i++) {
			keyStates[i] = false;
			prevKeyStates[i] = false;
		}
	}
	
	@Override
	public void startService() {
		resetStates();
		listeners.clear();
		active = true;
	}

	@Override
	public void stopService() {
		resetStates();
		listeners.clear();
		active = false;	
	}

	// Methods of the IKeyboard Service
	@Override
	public boolean isKeyPressed(int keyCode) {
		if (keyCode < 0 || keyCode >= TOTAL_KEYS)
			return false;
		
		return keyStates[keyCode];
	}

	@Override
	public boolean isKeyReleased(int keyCode) {
		if (keyCode < 0 || keyCode >= TOTAL_KEYS)
			return false;
		
		return !keyStates[keyCode];
	}

	@Override
	public boolean wasKeyPressed(int keyCode) {
		if (keyCode < 0 || keyCode >= TOTAL_KEYS)
			return false;
		
		return keyStates[keyCode] && !prevKeyStates[keyCode];
	}

	@Override
	public boolean wasKeyReleased(int keyCode) {
		if (keyCode < 0 || keyCode >= TOTAL_KEYS)
			return false;
		
		return !keyStates[keyCode] && prevKeyStates[keyCode];
	}
	
	/*
	 * withinRange(int lowerbound, int upperboundm, int value)
	 * Checks if value is within a given range for avoiding NO EXISTING KEY CODE ERROS
	 */
	private boolean withinRange(int lowerbound, int upperbound, int value) {
		return (value < lowerbound || value >= upperbound);
	}

	public void addKeyboardListener(KeyListener listener) {
		if (active) {
			listeners.add(listener);
		}
	}
	
	

	// KeyListener Methods for receiving key events from window
	@Override
	public void keyPressed(KeyEvent e) {
		if (active) {
			keyStates[e.getKeyCode()] = true;
			
			// Send Event to the KeyListeners registered
			for (KeyListener kl : listeners) {
				kl.keyPressed(e);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (active) {
			keyStates[e.getKeyCode()] = false;
			
			// Send Event to the KeyListeners registered
			for (KeyListener kl : listeners) {
				kl.keyReleased(e);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (active) {
			// Send Event to the KeyListeners registered
			for (KeyListener kl : listeners) {
				kl.keyTyped(e);
			}
		}
	}
	
	public void updatePrevKeyStates() {
		for (int i = 0; i < TOTAL_KEYS; i++) {
			prevKeyStates[i] = keyStates[i];
		}
	}

}
