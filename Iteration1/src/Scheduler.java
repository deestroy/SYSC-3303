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
	 * Constructor for Scheduler
	 * 
	 * @param elevators List of elevators
	 * 
	 * @param floors List of floors
	 */
	public Scheduler(FloorSubsystem floorsub) {
		this.floorsub = floorsub;
	}

	public Scheduler(FloorSubsystem floorsub, ArrayList<ButtonPress> queue) {
		this.floorsub = floorsub;
		this.queue = queue;
	}

	/**
	 * Find a command in the queue
	 * 
	 * @param task queried
	 */
	public synchronized void getQueueValue() {
		queue.get(0);
		notifyAll();
	}

	public ArrayList<ButtonPress> getQueue() {
		return this.queue;
	}

	public ArrayList<ButtonPress> getFloorQueue() {
		return this.floorQueue;
	}

	/**
	 * Add a new command to the queue
	 * 
	 * @param task added
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
				System.out.println("Scheduler found that FloorSubsytem is empty");

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
	
	public void addFloorQueue(ButtonPress buttonpress) {
		synchronized(this.floorQueue) {
		this.floorQueue.add(buttonpress);
		this.floorQueue.notifyAll();
	}
	}
}
