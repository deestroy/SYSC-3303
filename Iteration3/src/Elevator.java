
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * This class is meant to represent an elevator car object found in buildings
 * with multiple floors.
 */
public class Elevator implements Runnable {
	private ElevatorSubsystem subsys;
	private int currentFloor = 2; // All elevators start on the first floor.
	private int carId = 0;
	private boolean doorStatus = false; // True = Door is open, False = Door is closed
	private boolean motorStatus = false; // True = Motor is on, False = Motor is off
	private boolean currDirection = false; // True = Elevator is moving Up, False = Elevator is moving Down

	private double ELEVATORSPEED = 2.7; // m/s
	private double DOOR_OPEN_TIME = 0.0; // Time taken to open/close the elevator door
	private double LOAD_PASSENGER_TIME = 10.0; // Time taken for one passenger to get on the elevator when the door is
												// open
	private double ELEVATORACCELERATION = 0.9; // m/s^2
	private ArrayList<ButtonPress> taskList = new ArrayList<ButtonPress>();

	/**
	 * Constructor for the Elevator Class.
	 * 
	 * @param carId,  id of the elevator
	 * @param subsys, the instance of the subsystem
	 */
	public Elevator(int carId, ElevatorSubsystem subsys) {
		this.carId = carId;
		this.subsys = subsys;
	}

	/**
	 * Returns the number of the current floor the elevator is located.
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}

	/**
	 * Returns the current state of the motor
	 */
	public boolean getMotorStatus() {
		return this.motorStatus;
	}

	/**
	 * Sets the state of the motor
	 *
	 * @param motorStatus, boolean motor state
	 */
	public void setMotorStatus(boolean motorStatus) {
		if (motorStatus) {
			this.motorStatus = true;
			System.out.println("The motor is now on!");
			return;
		}
		this.motorStatus = false;
		System.out.println("The motor is now off!");
	}

	/**
	 * Sets the state of the door
	 *
	 * @param motorStatus, boolean door state
	 */
	public boolean getDoorStatus() {
		return this.doorStatus;
	}

	/**
	 * Returns the current state of the motor
	 */
	public void setDoorStatus(boolean doorStatus) {
		try {
			Thread.sleep((int) this.DOOR_OPEN_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (doorStatus) {
			this.doorStatus = true;
			System.out.printf("The door of elevator %d is now open!\n", this.carId);
			return;
		}
		this.doorStatus = false;
		System.out.printf("The door of elevator %d is now closed!\n", this.carId);
	}

	/**
	 * Returns the elevator car's ID number.
	 */
	public int getCarId() {
		return carId;
	}

	public void loadPassengers(int numPassengers) {
		try {
			Thread.sleep(numPassengers * (int) this.LOAD_PASSENGER_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Amount of time each elevator takes to get from one floor to another
	 * 
	 * @param startFloor, the starting floor of the elevator
	 * @param endFloor,   the ending floor of the elevator
	 */

	public int timeElevator(int startFloor, int endFloor) {
		int floorDistance = Math.abs(startFloor - endFloor);
		double travelDistance = Math.pow(ELEVATORSPEED, 2) / ELEVATORACCELERATION;
		double usualTime = (Math.sqrt(floorDistance)) * 2;

		// check if the elevator has the potential to get to the top speed and convert
		// it to milliseconds
		if (floorDistance <= travelDistance) {
			return (int) (Math.round(usualTime * 1000));
		} else {
			return (int) Math.round((Math.abs(floorDistance - travelDistance) / ELEVATORSPEED + usualTime) * 1000);
		}
	}

	// When the elevator arrives at a floor, it updates its current floor and checks
	// if there are passengers on that floor moving in the same direction.
	public int arrived_floor(int floorNum) {
		this.currentFloor = floorNum;
		System.out.printf("\nElevator %d is near floor %d!\n", this.carId, this.currentFloor);

		synchronized (this.taskList) {
			if (this.taskList.size() > 1) { // we have more than 1 task! check if any of them are at this floor:

				for (int i = this.taskList.size(); i > 0; i--) {
					if (taskList.get(i).getCarButton() == this.currentFloor) { // We have a task at this floor! check if
																				// it is in the same direction we're
																				// moving:
						System.out.println("Task at this floor!");
						if (taskList.get(i).getButtonDirection() == this.currDirection) { // Same direction we're
																							// moving! Stop for him!
							this.setMotorStatus(false); // Stop the motor
							this.setDoorStatus(true); // Open the door
							this.loadPassengers(1); // Load the passengers
							this.taskList.notifyAll();
							return (i);
						}
					}
				}
			}

		}
		return -1;
	}

	public int move_elevator(ButtonPress instruction) {

		int destination_floor = instruction.getFloorNumber();
		boolean direction = instruction.getButtonDirection();
		int initial_floor = this.currentFloor;
		// Calculate the total time of the trip:
		double tripTime = timeElevator(this.currentFloor, destination_floor);
		// Calculate the time taken to move 1 floor:
		double oneFloorTime = tripTime / Math.abs(this.currentFloor - destination_floor);

		this.setMotorStatus(true);

		int newTaskIndex = -1;

		this.currDirection = instruction.getButtonDirection();
		for (int floors_moved = 0; floors_moved <= Math.abs(initial_floor - destination_floor); floors_moved++) {
			if (newTaskIndex == -1) {
				if (direction) { // if we're moving up:
					newTaskIndex = arrived_floor(initial_floor + floors_moved);
				} else { // if we're moving down:
					newTaskIndex = arrived_floor(initial_floor - floors_moved);
				}
				if (floors_moved + 1 == Math.abs(initial_floor - destination_floor)) {
					continue;
				}
				try {
					Thread.sleep((int) oneFloorTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else {
				return newTaskIndex;
			}
		}
		this.setMotorStatus(false);
		return newTaskIndex;
	}

	/**
	 * This is the code that runs when the thread starts.
	 */
	public void run() {
		synchronized (this.subsys.getTaskList()) {
			while (true) {
				if (this.subsys.getTaskList().size() == 0) {
					System.out.println(LocalTime.now() + " ElevatorSubsystem queue is Empty ");
					try {
						this.subsys.getTaskList().wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				this.taskList.add(this.subsys.getTaskList().get(0));
				this.subsys.getTaskList().remove(0);
				System.out.println(LocalTime.now() + " Elevator receieved something!");

				System.out.println(LocalTime.now() + " Elevator departure time: ");
				int lampDirection = 2;
				if (currentFloor - this.taskList.get(0).getFloorNumber() > 0) {
					lampDirection = 1;
				} else if (currentFloor - this.taskList.get(0).getFloorNumber() < 0) {
					lampDirection = 0;
				}
				this.subsys.changeLampStatus(lampDirection);
				for (int i = 0; i < this.taskList.size(); i++) {
					System.out.println(this.taskList.get(i).getFloorNumber());
				}
				while(this.taskList.size() != 0) {
					int newTaskIndex = move_elevator(this.taskList.get(0));
					if(newTaskIndex == -1) {
						System.out.println("Task completed!\n\n");
					}
					else{move_elevator(this.taskList.get(newTaskIndex));}
				
				System.out.println(LocalTime.now() + " Elevator arrival time: ");
				this.subsys.changeLampStatus(2);

				this.subsys.addResponseList(this.taskList.get(0));
				this.taskList.remove(0);
				System.out.println(LocalTime.now() + " Elevator sent response to Response List ");
				}
			}
		}
	}
}
