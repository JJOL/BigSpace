package mdj2.bigspace.engine;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class EngineConfig {

	
	private final static int DIMENSIONS_M = 0x1;
	private final static int UPS_M        = 0x2;
	private final static int NAME_M       = 0x4;
	// Correctness stores the total correctness of the config defined
	private int correctness;
	
	private int gUPS;
	private int gWidth, gHeight;
	
	private String gName;
	
	public EngineConfig() {
		correctness = 0;
	}
	
	public void setGameName(String name) {
		unmark(NAME_M);
		if (name == null || name.trim().isEmpty())
			markError(NAME_M);
		
		this.gName = name;
	}
	
	public void setUPS(int ups) {
		unmark(UPS_M);
		if (ups > 120 || ups < 20)
			markError(UPS_M);
		gUPS = ups;
	}
	
	public void setWindowDimensions(int width, int height) {
		unmark(DIMENSIONS_M);
		if (width < 0 || height < 0)
			markError(DIMENSIONS_M);
		gWidth = width;
		gHeight = height;
	}
	
	public String getGameName() {
		return gName;
	}
	
	public int getUPS() {
		return gUPS;
	}
	
	public Dimension getWindowDimensions() {
		return new Dimension(gWidth, gHeight);
	}
	
	private void unmark(int mark) {
		correctness = correctness | mark;
	}
	
	private void markError(int mark) {
		correctness = correctness & (~mark);
	}
	
	public List<Integer> getErrors() {
		List<Integer> errors = new ArrayList<>();
		
		if ((correctness & DIMENSIONS_M) == 0) {
			errors.add(DIMENSIONS_M);
		}
		if ((correctness & UPS_M) == 0) {
			errors.add(UPS_M);
		}
		
		return errors;
	}
	
	public String getErrorMessage(int error) {
		String errMsg;
		
		switch (error) {
		case DIMENSIONS_M:
			errMsg = String.format("Invalid Window Dimensions specified in EngineConfig! (%d,%d)", 
					gWidth, gHeight);
			break;
		case UPS_M:
			errMsg = "Invalid UPS specified in EngineConfig!";
			break;
		default:
			errMsg = "Unknown error retrieved from EngineConfig!";
		}
		
		return errMsg;
	}
	
}
