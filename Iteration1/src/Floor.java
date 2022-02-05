package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Scanner;
/*
 * Basic Data about current status of floor
 * Takes the information in table and processes it
 */
/**
 * @author dhritiaravind
 *
 */
public class Floor {
	private boolean upPressed = false;
	private boolean downPressed = false;	//direction of travel
	private int floorNumber;
	private boolean lampOn = false;			//checks if floor is ready to receive an elevator
	private boolean arrival;
	
	//private String fileName = "/Users/dhritiaravind/git/SYSC-3303/Iteration1/testdata.txt"; //current location for the file
	
	
	public Floor(int FloorNumber) {
		this.floorNumber = FloorNumber;
	}
	
//	/**
//	 * Parse the data recieved in the input file
//	 * 
//	 * @param fileName - the name of the input file assuming it is .txt
//	 */
//	public void parseData(String fileName) {
//		this.fileName = fileName;
//		File file = new File(fileName);
//		
//		
//		// Referenced from: https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
//		try {
//			Scanner sc = new Scanner(file);
//			while (sc.hasNextLine()) {
//				// System.out.println(sc.nextLine()); //02:9:23.101 2 Down 1 \n 14:05:15.0 2 Up 4
//				String data = sc.nextLine();
//				
//				// create a new ArrayList with the data seperated
//				
//				
//			}
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	public static void main(String[] args) {
		Floor f = new Floor();
	}
	
}
