import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.time.LocalTime;
import java.util.ArrayList;

/* Client in system
 * THIS CLASS IS A PLACEHOLDER FOR FUTURE MILESTONES.
 * Elevators will make calls to the Scheduler
 * - After Scheduler response, The Elevator will then send the data back to the Schedule
 * 
 * The  elevator  controller  will  have  to  be  multi-threaded  since  it  is  expected  to 
handle more than one car at a time.  

 * */
public class ElevatorSubsystem implements Runnable {
	DatagramPacket sendPacket, receivePacket;
	DatagramSocket sendSocket, receiveSocket;
	private Elevator[] cars = new Elevator[1];
	private Scheduler sched;
	private ButtonPress recentPress;
	private ArrayList<ButtonPress> taskList = new ArrayList<ButtonPress>();
	private ArrayList<ButtonPress> responseList = new ArrayList<ButtonPress>();

	public ElevatorSubsystem(Scheduler sched) {
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

		this.sched = sched;
		this.cars[0] = new Elevator(0, this);
	}

	public void run() {
		while (true) {
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
				/*
				 * if (this.sched.getQueue().size() == 0) { System.out.println(LocalTime.now() +
				 * " Scheduler queue is Empty "); try { this.sched.getQueue().wait(); } catch
				 * (InterruptedException e) { e.printStackTrace(); }
				 * 
				 * }
				 */

				synchronized (this.taskList) {
					this.taskList.add(this.sched.getQueue().get(0));
					this.taskList.notifyAll();
				}
				this.sched.getQueue().remove(0);
				System.out.println(LocalTime.now() + " ElevatorSubsystem receieved something! ");
			}
		}
	}

	public ArrayList<ButtonPress> getTaskList() {
		return this.taskList;
	}

	/**
	 * Adds button presses to the response queue.
	 * 
	 * @param buttonpress, the button that will be added
	 */
	public void addResponseList(ButtonPress buttonpress) {
		synchronized (this.responseList) {
			this.responseList.add(buttonpress);
			this.responseList.notifyAll();
		}
	}

	/**
	 * Changes the status of the lamp
	 * 
	 * @param direction, direction of the elevator
	 */
	public void changeLampStatus(int direction) {
		this.sched.changeLampStatus(direction);
	}

}
