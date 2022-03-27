/**
 * 
 */
package general;

import java.time.LocalTime;

/**
 * @author Mohamed Selim ButtonPress is a container class for the messsages that
 *         are exchanged between the components of the system.
 */
public class ButtonPress {
	private boolean buttonDirection; // True for up, False for down
	private int carButton;
	private int floorNumber;
	private boolean activeButton; //Turn the button on = True, button off = False
	private LocalTime currTime = LocalTime.now();

	/**
	 * Constructor for a ButtonPress.
	 */
	public ButtonPress(boolean buttonDirection, int carButton, int floorNumber, LocalTime buttonTime) {
		this.buttonDirection = buttonDirection;
		this.carButton = carButton;
		this.floorNumber = floorNumber;
		this.currTime = buttonTime;
	}
	
	public ButtonPress(boolean buttonDirection, int floorNumber) {
		this.floorNumber = floorNumber;
		this.buttonDirection = buttonDirection;
	}
	
	
	/**
	 * Setter method for activeButton
	 */
	public boolean setActiveButton(boolean activeButton) {
		return this.activeButton = activeButton;
	}
	
	/**
	 * Getter method for activeButton
	 */
	public boolean getActiveButton() {
		return this.activeButton;
	}

	/**
	 * Getter method for buttonDirection
	 */
	public boolean getButtonDirection() {
		return this.buttonDirection;
	}

	/**
	 * Getter method for floorNumber
	 */
	public int getFloorNumber() {
		return this.floorNumber;
	}

	/**
	 * Getter method for carButton
	 */
	public int getCarButton() {
		return this.carButton;
	}

	/**
	 * Setter method for carButton
	 */
	public void setCarButton(int newButton) {
		this.carButton = newButton;
	}

	/*
	 * public String getPressTime() { return ""; }
	 */
}
