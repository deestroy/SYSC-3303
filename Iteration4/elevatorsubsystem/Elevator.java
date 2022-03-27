package elevatorsubsystem;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

import general.ButtonPress;

/**
 * This class is meant to represent an elevator car object found in buildings
 * with multiple floors.
 */
public class Elevator{
	private ElevatorSubsystem subsys;
	private boolean floorUpdated = false; //THe elevator has changed its position and needs to tell the Scheduler (through sybsystem)
	private int currentFloor = 2; // All elevators start on the first floor.
	private int[] plannedStops;
	private int carId = 0;
	private ButtonPress completedTask;
	private boolean doorStatus = false; // True = Door is open, False = Door is closed
	private boolean motorStatus = false; // True = Motor is on, False = Motor is off
	private boolean currDirection = false; // True = Elevator is moving Up, False = Elevator is moving Down

	private double ELEVATORSPEED = 2.7; // m/s
	private double DOOR_OPEN_TIME = 5000; // Time taken to open/close the elevator door
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

	public void setFloorUpdated(boolean floorChange) {
		this.floorUpdated = floorChange;
	}
	@Override
	public String toString() {
		String elevatorInfo = "The elevator is currently on floor: " + currentFloor;
		elevatorInfo += "\nThe motor of the elevator is currently: " + motorStatus;
		elevatorInfo += "\nThe door of the elevator is currently: " + doorStatus;
		elevatorInfo += "\nThe last direction in which the elevator has moved is: " + currDirection;
		return elevatorInfo;
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
	public void arrived_floor(int floorNum) {
		this.currentFloor = floorNum;

		System.out.printf("\nElevator %d is near floor %d!\n", this.carId, this.currentFloor);


	}

	public int move_elevator(ButtonPress instruction) {

		ArrayList<Integer> stops = new ArrayList<Integer>();

		int newTaskIndex = -1;
		int destination_floor = instruction.getFloorNumber();
		stops.add(destination_floor);
		int floors_moved = 0;
		boolean direction = instruction.getButtonDirection();
		int initial_floor = this.currentFloor;
		// Calculate the total time of the trip:
		double tripTime = timeElevator(this.currentFloor, destination_floor);
		// Calculate the time taken to move 1 floor:
		double oneFloorTime = tripTime / Math.abs(this.currentFloor - destination_floor);

		this.setMotorStatus(true);

		newTaskIndex = -1;
		this.currDirection = instruction.getButtonDirection();
		for (floors_moved = 0; floors_moved <= Math.abs(initial_floor - destination_floor); floors_moved++) {
			if (newTaskIndex == -1) {// No tasks to be picked up
				if (direction) { // if we're moving up:
					//newTaskIndex = arrived_floor(initial_floor + floors_moved);
				} else { // if we're moving down:
					//newTaskIndex = arrived_floor(initial_floor - floors_moved);
				}
				if (floors_moved + 1 == Math.abs(initial_floor - destination_floor)) {
					continue;
				}
				try {
					Thread.sleep((int) oneFloorTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else { // New task we can pick up along the way!
				// Add a new stop for it
				if (!(stops.contains(this.taskList.get(newTaskIndex)))) {
					stops.add(instruction.getFloorNumber());
					System.out.println("The currently planned stops are:" + stops);
				}
			}
		}

		this.completedTask = instruction;
		this.setMotorStatus(false);
		return newTaskIndex;
	}

	/**
	 * This is the code that runs when the thread starts.
	 */
	public static void main(String[] args) {
		//loop:

		//wait for task from elevatorSubsystem:

		//task added? do it. else, reloop ig

		//loop: for every floor traveled in task:
		//Is floor a planned stop? If so stop and do the shit (door open load passenger, remove from planned stop list, remove task from task list, etc)


		//Synchronize over floorUpdated:
		//YO SUBSYS I NEED TO REPORT (floorUpdated = true)

		//wait for elevator subsystem to reset floorUpdated to false (which means message delivered to scheduler)

		//Exit synchronized
		//New tasks? Change plans (add planned stops if needed) accordingly
		//move to next floor
		//Done! do the done stuff (same as "is floor a planned stop?" thing)

	}
}

