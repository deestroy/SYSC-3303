package src;

import java.time.LocalTime;

/**
* This class is meant to represent an elevator car object found in buildings with multiple floors. 
*/
public class Elevator implements Runnable {
	private Scheduler sched;
	private int currentFloor = 1; // All elevators start on the first floor.
	private boolean doorOpen = false;
	private boolean motorOn = false;
	private int carId = 0;
	private ButtonPress recentPress;

	/**
	* Constructor for the Elevator Class.
	*/
	public Elevator(int carId, Scheduler scheduler) {
		this.carId = carId;
		this.sched = scheduler;
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
	* This is the code that runs when the thread starts.
	*/
	public void run() {
		synchronized (this.sched.getQueue()) {
			while(true) {
			if (this.sched.getQueue().size() == 0) {
				System.out.println("Scheduler queue is Empty");
				try {
					this.sched.getQueue().wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			this.recentPress = this.sched.getQueue().get(0);
			this.sched.getQueue().remove(0);
			System.out.println("Elevator receieved something!");
			
			try {
				//The elevator executes the task. For now, this delay is a place holder.
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.sched.addFloorQueue(recentPress);
			System.out.println("Added Event to FloorQueue");
			}
		}
	}
}
