package elevatorsubsystem;

import java.time.LocalTime;
import java.util.ArrayList;

import general.ButtonPress;

import java.io.IOException;
import java.net.*;
/* Client in system
 * THIS CLASS IS A PLACEHOLDER FOR FUTURE MILESTONES.
 * Elevators will make calls to the Scheduler
 * - After Scheduler response, The Elevator will then send the data back to the Schedule
 * 
 * The  elevator  controller  will  have  to  be  multi-threaded  since  it  is  expected  to 
handle more than one car at a time.  

 * */

public class ElevatorSubsystem {
	DatagramPacket sendPacket, receivePacket;
	DatagramSocket sendSocket, receiveSocket;
	private Elevator[] cars = new Elevator[1];
	private DatagramPacket receivedTask, elevatorInfo;

	private ArrayList<ButtonPress> taskList = new ArrayList<ButtonPress>();

	public String elevatorInfo(int elevatorID) {
		return cars[elevatorID].toString();
	}

	public ElevatorSubsystem() {
		try {
			// Construct a datagram socket and bind it to any available
			// port on the local host machine.
			sendSocket = new DatagramSocket(304);

			// Construct a datagram socket and bind it to port 301
			// on the local host machine..
			receiveSocket = new DatagramSocket(303);

		} catch (SocketException se) {
			se.printStackTrace();
		}

		this.cars[0] = new Elevator(0, this);

		//
	public static void main(String[] args) {
		while (true) {
			// send status of all elevators to scheduler

			// Loop:
			// receive task from scheduler. timeout

			// send task to the elevator specified

			// new task? no? timeout

			// any elevator have something to report?:

			// send elevator info to scheduler
			// else: loop

		}

	}

	public void receiveFromSched() {
		// needs fixing according to how we do the implementation of elevsubsys
		byte first[] = new byte[5];
		receivePacket = new DatagramPacket(first, first.length);

		try {
			System.out.println("ElevatorSubsytem Waiting..."); // so we know we're waiting
			receiveSocket.receive(receivePacket);
		} catch (IOException e) {
			System.out.print("IO Exception: likely:");
			System.out.println("Receive Socket Timed Out.\n" + e);
			e.printStackTrace();
		}

		int len = receivePacket.getLength();
		String received;
		received = new String(receivePacket.getData(), 0, len);
		if (received.length() == 1) {
			synchronized (this.taskList) {
				this.taskList.add(this.sched.getQueue().get(0));
				this.taskList.notifyAll();
			}
			this.sched.getQueue().remove(0);
			System.out.println(LocalTime.now() + " ElevatorSubsystem receieved something! ");
		}
	}

	public void sendToElevator() {

	}

	public void sendToSched() {

	}

	public ArrayList<ButtonPress> getTaskList() {
		return this.taskList;
	}

	/**
	 * Changes the status of the lamp
	 * 
	 * @param direction, direction of the elevator
	 * 
	 *                   public void changeLampStatus(int direction) {
	 *                   this.sched.changeLampStatus(direction); }
	 */
}
