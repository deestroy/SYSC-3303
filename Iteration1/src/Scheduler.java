package src;
import java.util.ArrayList;


/*
 *
 * @author Dhriti
 * Communicates between ElevatorSubsystem and FloorSubsystem
 * - Scheduler will reply to the Elevator when there is work to be done
 * - After data is sent from to the elevator, it will forward the Data to the floor
 */
public class Scheduler implements Runnable {

	private ArrayList<ButtonPress> queue = new ArrayList<ButtonPress>();
	private ArrayList<ButtonPress> floorQueue = new ArrayList<ButtonPress>();
	private FloorSubsystem floorsub;

	/*
	 * Constructor for Scheduler class.
	 * 
	 */
	public Scheduler(FloorSubsystem floorsub) {
		this.floorsub = floorsub;
	}

	/**
	* Override constructor for Scheduler class including a queue.
	*/
	public Scheduler(FloorSubsystem floorsub, ArrayList<ButtonPress> queue) {
		this.floorsub = floorsub;
		this.queue = queue;
	}

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
		return this.floorQueue;
	}

	/**
	 * Adds button presses to the queue of button presses.
	 */
	public synchronized void addToQueue(ButtonPress btn) {
		queue.add(btn);
		notifyAll();
	}

	@Override
	public void run() {
		synchronized (this.floorsub.getInfo()) {
			while(true) {
			if (this.floorsub.getInfo().size() == 0) {
				System.out.println("Scheduler found that FloorSubsytem is empty" + LocalTime.now());

				try {
					//Thread.sleep(4000);
					this.floorsub.getInfo().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			queue.add(this.floorsub.getInfo().get(0));
			this.floorsub.getInfo().remove(0);
			System.out.println("Read from Floorsub");
			//this.floorsub.getInfo().notifyAll();
			}
		}
	}
	
	/**
	* Adds button presses to the floor queue.
	*/
	public void addFloorQueue(ButtonPress buttonpress) {
		synchronized(this.floorQueue) {
		this.floorQueue.add(buttonpress);
		this.floorQueue.notifyAll();
	}
	}
}
