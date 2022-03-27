
import java.time.LocalTime;
import java.util.ArrayList;
import java.net.*;
/* Client in system
 * THIS CLASS IS A PLACEHOLDER FOR FUTURE MILESTONES.
 * Elevators will make calls to the Scheduler
 * - After Scheduler response, The Elevator will then send the data back to the Schedule
 * 
 * The  elevator  controller  will  have  to  be  multi-threaded  since  it  is  expected  to 
handle more than one car at a time.  

 * */


public class ElevatorSubsystem{
	private Elevator[] cars = new Elevator[1];
	private DatagramPacket receivedTask, elevatorInfo;
	private DatagramSocket sendSocket, receiveSocket;

	private ArrayList<ButtonPress> taskList = new ArrayList<ButtonPress>();

	public String elevatorInfo(int elevatorID) {
		return cars[elevatorID].toString();
	}

	public ElevatorSubsystem() {
		this.cars[0] = new Elevator(0, this);
		try {
			sendSocket = new DatagramSocket();
			receiveSocket = new DatagramSocket(69);
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}
	}
	
	//
	public static void main(String[] args) {
		while (true) {
			//send status of all elevators to scheduler
			
			//Loop:
			//receive task from scheduler. timeout
			
			//send task to the elevator specified
			
			//new task? no? timeout
			
			//any elevator have something to report?:
			
				//send elevator info to scheduler
			//else: loop
						
		}
			
	}

	public void receiveFromSched() {
		
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
	 
	public void changeLampStatus(int direction) {
		this.sched.changeLampStatus(direction);
	}
	*/
}
