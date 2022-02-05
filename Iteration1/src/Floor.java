package src;
import java.time.LocalTime;
/*
 * Basic Data about current status of floor
 * Takes the information in table and processes it
 */
/**
 * @author dhritiaravind
 *
 */
public class Floor {
	private LocalTime timeL; // = LocalTime.now();
	private boolean upPressed;
	private boolean downPressed;	//direction of travel
	private int initalFloor; 		//user's current floor
	private int finalFloor;			//user's Destination floor
	private boolean lampOn;			//checks if floor is ready to receive an elevator
	private boolean arrival;
	
	
	public Floor() {
		// parseData();
		
	}
	
	/**
	 * Parse the data recieved in the input file
	 * 
	 * @param fileName - the name of the input file assuming it is .txt
	 */
	public void parseData(String fileName) {
		
		
	}
}
