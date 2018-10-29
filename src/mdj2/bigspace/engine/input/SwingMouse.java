package mdj2.bigspace.engine.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import mdj2.bigspace.engine.services.IService;

public class SwingMouse implements IMouse, MouseListener, MouseMotionListener, IService {

	public static final int M_LEFT = MouseEvent.BUTTON1, M_RIGHT = MouseEvent.BUTTON3;
	
	
	// Amount of Keys
	private final int TOTAL_BUTTONS = 5;
	// Table for Holding the current states of the keys
	private boolean btnsStates[];
	//Table for Holding the previous states of the keys
	private boolean prevBtnsStates[];
	
	// Mouse Position
	private int mouseX, mouseY;
	
	// Control
	private boolean active;
	private List<MouseListener> listeners;
	
	public SwingMouse() {
		btnsStates     = new boolean[TOTAL_BUTTONS];
		prevBtnsStates = new boolean[TOTAL_BUTTONS];
		
		mouseX = 0;
		mouseY = 0;
		
		listeners = new ArrayList<>();
		
		active = false;
	}
	
	private void resetMouseStates() {
		for (int i = 0; i < TOTAL_BUTTONS; i++) {
			btnsStates[i]     = false;
			prevBtnsStates[i] = false;
		}
	}
	
	public void updatePrevBtnsStates() {
		for (int i = 0; i < TOTAL_BUTTONS; i++) {
			prevBtnsStates[i] = btnsStates[i];
		}
	}
	
	// IService Methods
		@Override
		public void startService() {
			active = true;
			resetMouseStates();
			listeners.clear();
		}

		@Override
		public void stopService() {
			active = false;
			resetMouseStates();
			listeners.clear();
		}
	
	// Window Mouse Events
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if (active) {
			btnsStates[e.getButton()] = true;
			
			// Call Listeners
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (active) {
			btnsStates[e.getButton()] = false;
			
			// Call Listeners
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (active) {
			mouseX = e.getX();
			mouseY = e.getY();
			
			// Call Listeners
		}
	}

	// IMouse Methods
	@Override
	public boolean isLeftPressed() {
		return btnsStates[M_LEFT];
	}

	@Override
	public boolean isLeftReleased() {
		return !btnsStates[M_LEFT];
	}

	@Override
	public boolean isRightPressed() {
		return btnsStates[M_RIGHT];
	}

	@Override
	public boolean isRightReleased() {
		return btnsStates[M_RIGHT];
	}

	@Override
	public boolean wasLeftPressed() {
		return !prevBtnsStates[M_LEFT] && isLeftPressed();
	}

	@Override
	public boolean wasLeftReleased() {
		return prevBtnsStates[M_LEFT] && isLeftReleased();
	}

	@Override
	public boolean wasRightPressed() {
		return !prevBtnsStates[M_RIGHT] && isRightPressed();
		
	}

	@Override
	public boolean wasRightReleased() {
		return prevBtnsStates[M_RIGHT] && isRightReleased();
	}

	@Override
	public int getMouseX() {
		return mouseX;
	}

	@Override
	public int getMouseY() {
		return mouseY;
	}

	@Override
	public void addMouseListener(MouseListener listener) {
		listeners.add(listener);
	}

	

}
