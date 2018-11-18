package mdj2.bigspace.engine.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import mdj2.bigspace.engine.GameEngine;
import mdj2.bigspace.engine.input.SwingKeyboard;
import mdj2.bigspace.engine.input.SwingMouse;

public class CanvasDisplay extends JFrame implements IDisplay, WindowListener {

	private final int SWIDTH, SHEIGHT;
	
	private GameEngine engine;
	
	private String gameTitle;
	
	private Canvas canvas;
	private Graphics2D g2d;
	private BufferStrategy bs;

	public CanvasDisplay(GameEngine engine, Dimension dimension, String gName, SwingKeyboard keyboard, SwingMouse mouse) {
		System.out.println("Initializing JPanel");
		this.engine = engine;
		// JPanel Options
		SWIDTH  = (int) dimension.getWidth();
		SHEIGHT = (int) dimension.getHeight();
		gameTitle = gName;
		setupWindow();
		
		if (keyboard != null)
			canvas.addKeyListener(keyboard);
		if (mouse != null) {
			canvas.addMouseListener(mouse);
			canvas.addMouseMotionListener(mouse);
		}
	}
	
	private void setupWindow() {
		setSize(SWIDTH, SHEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle(gameTitle);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addWindowListener(this);
		
		canvas = new Canvas();
		canvas.setSize(SWIDTH, SHEIGHT);
		add(canvas);
		pack();
		canvas.createBufferStrategy(2);
	}
	
	public void showWindow() {
		canvas.setFocusable(true);
		canvas.requestFocus();
		setVisible(true);
	}
	
	public void hideWindow() {
		
	}
	
	public void disposeWindow() {
		dispose();
	}
	
	// Start the execution of the thread until we are sure the panel is composed in the window
	/*public void addNotify() {
		super.addNotify();
		System.out.println("Added Component!");
		System.out.println("Calling startGame()...");
		engine.ready(); ?
	}*/
	
	
	// Draw Game GUI and Objects
	public void renderWindow() {
		bs = canvas.getBufferStrategy();
		if (bs == null) {
			System.out.println("Cant get Buffer Strategy!!");
			canvas.createBufferStrategy(2);
			return;
		}
		g2d = (Graphics2D) bs.getDrawGraphics();
		
		// Clear Screen
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Render Game
		engine.gRender(g2d);
		
		// Free Graphics
		g2d.dispose();
		
		paintGraphics();
	}
	
	// Update Pixels of Screen
	private void paintGraphics() {
		if (bs != null)
			bs.show();
	}

	
	/* Window Listener Events for detecting closing */
	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	// Handle User Exiting Application through Close X Button
	@Override
	public void windowClosing(WindowEvent e) {
		//TODO Handle User Closing Game
		engine.onDisplayClosed();
		System.out.println("[GameEngine_Window-Alert] User Closing Application!");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}
	
}
