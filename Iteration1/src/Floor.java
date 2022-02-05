package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Scanner;
/*
 * Basic Data about current status of floor
 * Takes the information in table and processes it
 */

public class Floor {
	private boolean upPressed = false;
	private boolean downPressed = false;	//direction of travel
	private int floorNumber;
	private boolean lampOn = false;			//checks if floor is ready to receive an elevator
	private boolean arrival;
		
	
	public Floor(int FloorNumber) {
		this.floorNumber = FloorNumber;
	}
	
//	
	
}
