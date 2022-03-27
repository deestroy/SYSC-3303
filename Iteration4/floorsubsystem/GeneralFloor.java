package floorsubsystem;

import general.ButtonPress;
/**
 * 
 * Creates an average floor
 * @author dhritiaravind
 *
 */
public class GeneralFloor extends Floor{
	private ButtonPress upButton;
	private ButtonPress downButton;

	public GeneralFloor(int FloorNumber) {
		super(FloorNumber);
		this.downButton = new ButtonPress(false, FloorNumber);
		this.upButton = new ButtonPress(true, FloorNumber);
		
	}
	
	/**
	 * Sets up floor button to be active
	 */
	public void setUpButton(boolean status) {
		upButton.setActiveButton(status);
	}
	
	/**
	 * Sets down floor button to be active
	 */
	public void setDownButton(boolean status) {
		downButton.setActiveButton(status);
	}

}
