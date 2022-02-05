package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

/*
 * Communicates between ElevatorSubsystem and FloorSubsystem
 * - Scheduler will reply to the Elevator when there is work to be done
 * - After data is sent from to the elevator, it will forward the Data to the floor
 */
public class Scheduler implements Runnable{
	private boolean elevatorReady; //elevator ready to receive requests
	private boolean elevatorTask; //If the elevator is taking data from the File
	private boolean floorTask; //If the floor is taking new data
	private HashMap<Integer, ArrayList<String>> userData = new HashMap<Integer, ArrayList<String>>(); //parsed data
	private String fileName = "/Users/dhritiaravind/git/SYSC-3303/Iteration1/testdata.txt"; //current location for the file
	
	/**
	 * 
	 */
	public Scheduler() {
		
	}

	/**
	 * Parse the data received in the input file
	 * 
	 * @param fileName - the name of the input file assuming it is .txt
	 */
	public void parseData(String fileName) {
		this.fileName = fileName;
		File file = new File(fileName);
		
		
		// Referenced from: https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				// System.out.println(sc.nextLine()); //02:9:23.101 2 Down 1 \n 14:05:15.0 2 Up 4
				String data = sc.nextLine();
				
				
				
				// create a new ArrayList with the data separated
				
				
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override 
	public void run() {
		
	}
	

}
