package floorsubsystem;

import general.ButtonPress;

/**
 * Creates special exceptions for the bottom floor (first floor)
 * @author dhritiaravind
 *
 */
public class BottomFloor extends Floor{
	
	private ButtonPress upButton;
	public BottomFloor(int FloorNumber) {
		super(FloorNumber);
		this.upButton = new ButtonPress(true, FloorNumber);
		
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Sets up floor button to be active
	 */
	public void setUpButton(boolean status) {
		upButton.setActiveButton(status);
	}

}
