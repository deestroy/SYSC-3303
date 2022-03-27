
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

	public static void main(String[] args) {
		//receive all initial elevator info from elevator Subsystem
		
		//Loop:
			//wait for tasks from floorsubsys
		
			//any updates on elevator info? timeout
			
			//Determine which elevator should do the task
			
			//Send task info to elevatorSubsys (button press + 1 bit that determines which elevator should do it)
			
			
		//The code under this line should go under a new function that "waits for tasks from floorsubsys"
		//Lets make a bunch of functions!
		/*synchronized (this.floorsub.getInfo()) {
			while (true) {
				if (this.floorsub.getInfo().size() == 0) {
					System.out.println(
							LocalTime.now() + " Scheduler found that FloorSubsytem doesn't have any new jobs for it.");

					try {
						this.floorsub.getInfo().wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				synchronized (queue) {
					queue.add(this.floorsub.getInfo().get(0));
					this.queue.notifyAll();
				}
				this.floorsub.getInfo().remove(0);
				System.out.println(LocalTime.now() + " Scheduler received job from Floor Subsystem.");
			}
		}*/
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
