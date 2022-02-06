package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/* author: Marwan Zeyada
 * 
 * Client in system
 * 
 * To read events in the format: Time, floor, floor direction, and elevator button,
 * Each line of input is to be sent to the Scheduler
 * */


public class FloorSubsystem {
	private ArrayList<ButtonPress> info;
	private ArrayList<ButtonPress> userData = new ArrayList<ButtonPress>(); //parsed data
	private String fileName = "/Users/dhritiaravind/git/SYSC-3303/Iteration1/testdata.txt"; //current location for the file

	public FloorSubsystem() {
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
				String[] splited = data.split(" ");

				// create a new ArrayList with the data separated
				userData.add(new ButtonPress(splited[2],Integer.parseInt(splited[1]), Integer.parseInt(splited[3]), LocalTime.parse(splited[0])));	


			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addIn(ButtonPress buttonpress) {
		this.info.add(buttonpress);
	}

	public void removeOut(ArrayList<Object> removee) {
		info.remove(removee);
	}

	public void removeOut(int index) {
		info.remove(index);
	}

	public ArrayList<ButtonPress> getInfo(){
		return info;
	}

}
