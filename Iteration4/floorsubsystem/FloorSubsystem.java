package floorsubsystem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import general.ButtonPress;

/* author: Dhriti Aravind & Marwan Zeyada
 * 
 * Client in system
 * 
 * To read events in the format: Time, floor, floor direction, and elevator button,
 * Each line of input is to be sent to the Scheduler
 * */

public class FloorSubsystem {
	DatagramPacket sendPacket, receivePacket;
	DatagramSocket sendReceiveSocket;
	private ArrayList<ButtonPress> info = new ArrayList<ButtonPress>();
	private final Floor[] floorList = { new TopFloor(1), new GeneralFloor(2), new GeneralFloor(3), new GeneralFloor(4), new GeneralFloor(5),
			new BottomFloor(6) };
	private String fileName = "placeholder"; // current location for the file

	public FloorSubsystem() {

		try {
			// Construct a datagram socket and bind it to any available
			// port on the local host machine. This socket will be used to
			// send and receive UDP Datagram packets.
			sendReceiveSocket = new DatagramSocket(300);
		} catch (SocketException se) {
			se.printStackTrace();
		}

	}

	/**
	 * Parse the data received in the input file
	 * 
	 * @param fileName - the name of the input file assuming it is .txt
	 */
	public void parseData(String fileName) {
		this.fileName = fileName;
		File file = new File(fileName);

		// Referenced from:
		// https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				// System.out.println(sc.nextLine()); //02:9:23.101 2 Down 1 \n 14:05:15.0 2 Up
				// 4
				String data = sc.nextLine();
				String[] splited = data.split(" ");

				// create a new ArrayList with the data separated
				boolean direction = false;
				if (splited[2].equals("Up")) {
					direction = true;
				}

				byte msg[] = data.getBytes(StandardCharsets.UTF_8);

				try {
					sendPacket = new DatagramPacket(msg, msg.length, InetAddress.getLocalHost(), 301);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}

				try {
					sendReceiveSocket.send(sendPacket);
					System.out.println("\nFloorSubsystem Sent Instruction to Scheduler Succesfully\n");
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(1);
				}

				// this.info.add(new ButtonPress(direction,Integer.parseInt(splited[1]),
				// Integer.parseInt(splited[3]), LocalTime.parse(splited[0])));

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
	public ArrayList<ButtonPress> getInfo() {
		return info;
	}

	public void changeLampStatus(int direction) {
		for (int i = 0; i < this.floorList.length; i++) {
			this.floorList[i].changeLampStatus(direction);
		}
	}

}
