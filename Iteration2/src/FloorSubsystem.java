package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/* author: Dhriti Aravind & Marwan Zeyada
 * 
 * Client in system
 * 
 * To read events in the format: Time, floor, floor direction, and elevator button,
 * Each line of input is to be sent to the Scheduler
 * */


public class FloorSubsystem {
	private ArrayList<ButtonPress> info = new ArrayList<ButtonPress>();
	private final Floor[] floorList = {new Floor(1), new Floor(2), new Floor(3), new Floor(4), new Floor(5), new Floor(6)};
	private String fileName = "C:\\Users\\zeyad\\Documents\\test.txt"; //current location for the file

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
				boolean direction = false;
				if(splited[2].equals("Up")) {
					direction = true;
				}
				this.info.add(new ButtonPress(direction,Integer.parseInt(splited[1]), Integer.parseInt(splited[3]), LocalTime.parse(splited[0])));	


			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * adds a ButtonPress to the info arraylist
	 * 
	 * @param ButtonPress - the add a button press
	 */
	public void addIn(ButtonPress buttonpress) {
		this.info.add(buttonpress);
	}

	/**
	 * removes a ButtonPress to the info arraylist
	 * 
	 * @param ButtonPress - the removee
	 */
	public void removeOut(ArrayList<Object> removee) {
		info.remove(removee);
	}

	/**
	 * removes a ButtonPress to the info arraylist using an index
	 * 
	 * @param index - int the index of the removee
	 */
	public void removeOut(int index) {
		info.remove(index);
	}

	/**
	 * gets info the buttonpress arraylist
	 * 
	 *
	 */
	public ArrayList<ButtonPress> getInfo(){
		return info;
	}
	public void changeLampStatus(int direction) {
		for(int i = 0; i < this.floorList.length; i++) {
			this.floorList[i].changeLampStatus(direction);
		}
	}

}
