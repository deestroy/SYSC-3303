
import java.time.LocalTime;
import java.util.ArrayList;

/*
 *
 * @author Dhriti
 * Communicates between ElevatorSubsystem and FloorSubsystem
 * - Scheduler will reply to the Elevator when there is work to be done
 * - After data is sent from to the elevator, it will forward the Data to the floor
 */
public class Scheduler {

  DatagramPacket sendPacket, receivePacket;
	DatagramSocket sendSocket, receiveSocket;
  private ArrayList<String> stringQueue = new ArrayList<String>();
	private ArrayList<ButtonPress> queue = new ArrayList<ButtonPress>();
	private ArrayList<ButtonPress> responseLog = new ArrayList<ButtonPress>();
	private FloorSubsystem floorsub;
	// private ElevatorSubsystem subsys;

	/*
	 * Constructor for Scheduler class.
	 * 
	 * @param floorsub FloorSubsystem instance
	 */
	public Scheduler(FloorSubsystem floorsub) {
    try {
			// Construct a datagram socket and bind it to any available
			// port on the local host machine.
			sendSocket = new DatagramSocket(302);

			// Construct a datagram socket and bind it to port 301
			// on the local host machine..
			receiveSocket = new DatagramSocket(301);

		} catch (SocketException se) {
			se.printStackTrace();
		}
    
		this.floorsub = floorsub;
	}

	/**
	 * Override constructor for Scheduler class including a queue.
	 * 
	 * @param floorsub, the floor subsystem we are working with
	 * @param queue,    the queue of pending requests
	 */
	public Scheduler(FloorSubsystem floorsub, ArrayList<ButtonPress> queue) {
		this.floorsub = floorsub;
		this.queue = queue;
	}

	/**
	 * gets the value of an element in the queue
	 */
	public synchronized void getQueueValue() {
		queue.get(0);
		notifyAll();
	}

	/**
	 * Returns a queue of all the button presses.
	 */
	public ArrayList<ButtonPress> getQueue() {
		return this.queue;
	}

	/**
	 * Returns the queue of the floors.
	 */
	public ArrayList<ButtonPress> getFloorQueue() {
		return this.responseLog;
	}

	/**
	 * Adds button presses to the queue of button presses.
	 * 
	 * @param btn, the button we are supposed to be adding to the queue
	 */
	public synchronized void addToQueue(ButtonPress btn) {
		queue.add(btn);
		notifyAll();
	}

  public void receiveCommand() {
		while (true) {
			byte first[] = new byte[50];
			receivePacket = new DatagramPacket(first, first.length);

			try {
				System.out.println("Scheduler Waiting..."); // so we know we're waiting
				receiveSocket.receive(receivePacket);
			} catch (IOException e) {
				System.out.print("IO Exception: likely:");
				System.out.println("Receive Socket Timed Out.\n" + e);
				e.printStackTrace();
			}

			int len = receivePacket.getLength();
			String received = new String(receivePacket.getData(), 0, len);
      stringQueue.add(received);
      

			System.out.println(received);

			String[] splited = received.split(" ");

			boolean direction = false;
			if (splited[2].equals("Up")) {
				direction = true;
			}
			
			this.queue.add(new ButtonPress(direction, Integer.parseInt(splited[1]), Integer.parseInt(splited[3]),
					LocalTime.parse(splited[0])));
			
			byte notify[] = {1};
			
			try {
				sendPacket = new DatagramPacket(notify, notify.length, InetAddress.getLocalHost(), 303);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.exit(1);
			}
			
			try {
				sendSocket.send(sendPacket);
				System.out.println("\nScheduler Sent Notification to ElevatorSubsystem\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

  public void sendCommand() {
		//x = specified command index in list
		//replace in the get(0)
		//carID = target elevator
		int carID=2;
		byte a[] = stringQueue.get(0).getBytes(StandardCharsets.UTF_8);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		try {
			outputStream.write( a );
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		outputStream.write( 0 );
		outputStream.write( carID );

		byte c[] = outputStream.toByteArray( );
		
		try {
			sendPacket = new DatagramPacket(c, c.length, InetAddress.getLocalHost(), 303);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			sendSocket.send(sendPacket);
			System.out.println("\nScheduler Sent Instruction to ElevatorSubsystem Succesfully\n");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

	public static void main(String[] args) {
		//receive all initial elevator info from elevator Subsystem
		
		//Loop:
			//wait for tasks from floorsubsys
      sched.receiveCommand();
		
			//any updates on elevator info? timeout
			
			//Determine which elevator should do the task
			
			//Send task info to elevatorSubsys (button press + 1 bit that determines which elevator should do it
			
//Lets make a bunch of functions!
	}

	/**
	 * Adds button presses to the floor queue.
	 * 
	 * @param adds a button press to the queue
	 */
	public void addFloorQueue(ButtonPress buttonpress) {
		synchronized (this.responseLog) {
			this.responseLog.add(buttonpress);
			this.responseLog.notifyAll();
		}
	}

	/**
	 * Add the direction of the lamp
	 * 
	 * @param the direction of the lamp
	 */
	public void changeLampStatus(int direction) {
		this.floorsub.changeLampStatus(direction);
	}
}