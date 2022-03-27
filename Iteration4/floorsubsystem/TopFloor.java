package floorsubsystem;

import general.ButtonPress;

/**
 * Creates special exceptions for the top floor (6th floor)
 * @author dhritiaravind
 *
 */
public class TopFloor extends Floor{
	
	private ButtonPress downButton;
	
	public TopFloor(int FloorNumber) {
		super(FloorNumber);
		this.downButton = new ButtonPress(false, FloorNumber);
		
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Sets down floor button to be active
	 */
	public void setDownButton(boolean status) {
		downButton.setActiveButton(status);
	}
	
	

}
