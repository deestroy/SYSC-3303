package src;

import java.time.LocalTime;

/**
* This class is meant to represent an elevator car object found in buildings with multiple floors. 
*/
public class Elevator implements Runnable {
	private Scheduler sched;
  private static ElevatorSubsystem subsys; 
	private int currentFloor = 1; // All elevators start on the first floor.
	private boolean doorOpen = false;
	private boolean motorOn = false;
	private int carId = 0;
	private double ELEVATORSPEED = 2.7; //m/s
	private double ELEVATORACCELERATION = 0.9; //m/s^2
	private ButtonPress recentPress;

	/**
	* Constructor for the Elevator Class.
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
	 */
	
	public int timeElevator(int startFloor, int endFloor) {
		
		int floorDistance = Math.abs(startFloor-endFloor);
		double travelDistance = Math.pow(elevatorSpeed, 2) / elevatorAcceleration;
		double usualTime = (Math.sqrt(floorDistance)) * 2;
		
		//check if the elevator has the potential to get to the top speed and convert it to milliseconds
		if (floorDistance <= travelDistance) {
			return (int) (Math.round(usualTime * 1000));
		}
		else {
			return (int) Math.round((Math.abs(floorDistance-travelDistance)/elevatorSpeed + usualTime)* 1000);
		}

	}

	/**
	* This is the code that runs when the thread starts.
	*/
	public void run() {
		synchronized (this.subsys.getTaskList() {
			while(true) {
			if (this.subsys.getTaskList().size() == 0) {
				System.out.println("ElevatorSubsystem queue is Empty " + LocalTime.now());
				try {
					this.subsys.getTaskList().wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			this.recentPress = this.subsys.getTaskList().get(0);
			this.subsys.getTaskList().remove(0);
			System.out.println("Elevator receieved something!");
      );
			
			try {
				//The elevator executes the task. For now, this delay is a place holder.
        System.out.println("Elevator departure time: " + LocalTime.now()
				Thread.sleep(timeElevator(currentFloor, this.recentPress.getFloorNumber()));
        System.out.println("Elevator arrival time: " + LocalTime.now());
        currentFloor = this.recentPress.getFloorNumber();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.subsys.addResponseList(recentPress);
			System.out.println("Elevator sent response to Response List " + LocalTime.now());
			}
		}
	}
}
