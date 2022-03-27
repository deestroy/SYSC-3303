package floorsubsystem;

public class Lamp {
	private boolean lampActive; // True for on, False for off
	private boolean lampDirection;
	private int floorNumber;
//	private boolean upLamp;
//	private boolean downLamp;
	
	public Lamp(boolean lampDirection, int floorNumber) {
		this.floorNumber = floorNumber;
		this.lampDirection = lampDirection;
	}
	
	/**
	 * Setter method for lampActive
	 */
	public boolean setActiveLamp(boolean lampActive) {
		return this.lampActive = lampActive;
	}
	
	/**
	 * Getter method for activeButton
	 */
	public boolean getActiveLamp() {
		return this.lampActive;
	}

}
