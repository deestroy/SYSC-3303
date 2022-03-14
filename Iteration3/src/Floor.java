import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Scanner;

/*
 * Basic Data about current status of floor
 * Takes the information in table and processes it
 * Represents a floor of a building.
 */

/**
 * @author Mohamed Selim
 */
public class Floor{
	private int floorNumber;
	private boolean upLamp;
	private boolean downLamp;
	private ButtonPress recentPress;

	/**
	* Constructor for the floor class.
	*/
	public Floor(int FloorNumber) {
		this.floorNumber = FloorNumber;
	}
	
	/**
	* Returns the number of the floor.
	*/
	public int getFloorNumber() {
		return this.floorNumber;
	}


  /**
  * Changes the status of the lamp
  * @param direction, direction of the elevator
  */
  public void changeLampStatus(int direction) {
	  switch(direction) {
	  case 0:
		  this.downLamp = false;
		  this.upLamp = true;
		  System.out.println("FLOOR "+ floorNumber + " UP LAMP TURNED ON");
		  break;
	  case 1:
		  this.downLamp = true;
		  this.upLamp = false;
		  System.out.println("FLOOR "+ floorNumber + " DOWN LAMP TURNED ON");
		  break;
	  case 2:
		  this.downLamp = false;
		  this.upLamp = false;
		  System.out.println("ALL LAMPS TURNED OFF");
		  break;
	  }
  }

	/**
	* Indicates the button that was most recently pressed.
	*/
	public ButtonPress getRecentPress() {
		return this.recentPress;
	}
}

