/**
 * 
 */
package floorsubsystem;

import general.ButtonPress;


/**
 * @author Mohamed Selim
 * 
 * Floor class represents a floor of a building.
 */
public class Floor {
	protected final int FLOORNUMBER;
//	protected ButtonPress upButton;
//	protected ButtonPress downButton;
	protected Lamp downLamp;
	protected Lamp upLamp;

	/**
	 * Constructor for the floor class.
	 */
	public Floor(int FloorNumber) {
		this.FLOORNUMBER = FloorNumber;
		this.upLamp = new Lamp(true, FloorNumber);
		this.downLamp = new Lamp(false, FloorNumber);
//		this.downButton = new ButtonPress(false, FloorNumber);
//		this.upButton = new ButtonPress(true, FloorNumber);
	}
	
	/**
	 * Sets down floor lamp to be active
	 */
	public void setDownLamp(boolean status) {
		downLamp.setActiveLamp(status);
	}
	
	/**
	 * Sets up floor lamp to be active
	 */
	public void setUpLamp(boolean status) {
		upLamp.setActiveLamp(status);
	}

	/**
	 * Returns the number of the floor.
	 * 
	 * @return int the floor number
	 */
	public int getFloorNumber() {
		return this.FLOORNUMBER;
	}

	/**
	 * Changes the status of the lamp
	 * 
	 * @param direction, direction of the elevator
	 */
	public void changeLampStatus(int direction) {
		switch (direction) {
		case 0:
			setDownLamp(false);
			setUpLamp(true);
			System.out.println("FLOOR " + FLOORNUMBER + " UP LAMP TURNED ON");
			break;
		case 1:
			setDownLamp(false);
			setUpLamp(false);
			System.out.println("FLOOR " + FLOORNUMBER + " DOWN LAMP TURNED ON");
			break;
		case 2:
			setDownLamp(false);
			setUpLamp(false);
			System.out.println("ALL LAMPS TURNED OFF");
			break;
		}
	}
	
	

}
