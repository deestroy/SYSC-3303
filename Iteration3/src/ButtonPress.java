import java.time.LocalTime;

/**
 * @author Mohamed Selim
 * ButtonPress is a container class for the messsages that are exchanged between the components of the system.
 */
public class ButtonPress {
	private boolean buttonDirection; //True for up, False for down
	private int carButton;
	private int floorNumber;
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

	/*public String getPressTime() {
		return "";
	}*/
}
