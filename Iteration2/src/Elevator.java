package src;

import java.time.LocalTime;

/**
 * This class is meant to represent an elevator car object found in buildings
 * with multiple floors.
 */
public class Elevator implements Runnable {
	private ElevatorSubsystem subsys;
	private int currentFloor = 2; // All elevators start on the first floor.
	private int carId = 0;
	private double ELEVATORSPEED = 2.7; // m/s
	private double ELEVATORACCELERATION = 0.9; // m/s^2
	private ButtonPress recentPress;

	/**
	 * Constructor for the Elevator Class.
   * @param carId, id of the elevator
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
	 * Returns the elevator car's ID number.
	 */
	public int getCarId() {
		return carId;
	}

	/**
	 * Amount of time each elevator takes to get from one floor to another
   * @param startFloor, the starting floor of the elevator
   * @param endFloor, the ending floor of the elevator
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
				this.recentPress = this.subsys.getTaskList().get(0);
				this.subsys.getTaskList().remove(0);
				System.out.println(LocalTime.now() + " Elevator receieved something!");

				try {
					
					System.out.println(LocalTime.now() + " Elevator departure time: ");
					int lampDirection = 2;
					if(currentFloor - this.recentPress.getFloorNumber() > 0) {
						lampDirection = 1;
					}
					else if (currentFloor - this.recentPress.getFloorNumber() < 0){
						lampDirection = 0;
					}
					this.subsys.changeLampStatus(lampDirection);
					Thread.sleep(timeElevator(currentFloor, this.recentPress.getFloorNumber()));
					System.out.println(LocalTime.now() + " Elevator arrival time: ");
					currentFloor = this.recentPress.getFloorNumber();
					this.subsys.changeLampStatus(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				this.subsys.addResponseList(recentPress);
				System.out.println(LocalTime.now() + " Elevator sent response to Response List ");
			}
		}
	}
}
