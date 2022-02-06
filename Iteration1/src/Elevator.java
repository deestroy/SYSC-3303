package src;

import java.time.LocalTime;

public class Elevator implements Runnable {
	private Scheduler sched;
	private int currentFloor = 1; // All elevators start on the first floor.
	private boolean doorOpen = false;
	private boolean motorOn = false;
	private int carId = 0;
	private ButtonPress recentPress;

	public Elevator(int carId, Scheduler scheduler) {
		this.carId = carId;
		this.sched = scheduler;
	}

	/*
	private void pressButton(int floorNumber) {
		this.recentPress = new ButtonPress((currentFloor - floorNumber >= 0), floorNumber, currentFloor, LocalTime.now());
		//this.sched.addTask(recentPress);
	}
	*/

	public int getCurrentFloor() {
		return currentFloor;
	}

	public int getCarId() {
		return carId;
	}

	public void run() {
		synchronized (this.sched.getQueue()) {
			while(true) {
			if (this.sched.getQueue().size() == 0) {
				System.out.println("sched queue empty");
				try {
					//Thread.sleep(4000);
					this.sched.getQueue().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			this.recentPress = this.sched.getQueue().get(0);
			this.sched.getQueue().remove(0);
			System.out.println("Receieved something");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.sched.addFloorQueue(recentPress);
			System.out.println("Gave to floor queue");
			}
		}
	}
}
