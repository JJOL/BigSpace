package mdj2.bigspace.engine.input;

import java.awt.event.MouseListener;

public interface IMouse {

	public boolean isLeftPressed();	
	public boolean isLeftReleased();
	
	public boolean isRightPressed();
	public boolean isRightReleased();
	
	public boolean wasLeftPressed();
	public boolean wasLeftReleased();
	
	public boolean wasRightPressed();
	public boolean wasRightReleased();
	
	public int getMouseX();
	public int getMouseY();
	
	public void addMouseListener(MouseListener listener);
	
	
}
