package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Scanner;
/*
 * Basic Data about current status of floor
 * Takes the information in table and processes it
 */

/**
 * @author Mohamed Selim
 */
public class Floor implements Runnable {
	private boolean buttonDirection; // True for up. False for down
	private int floorNumber;
	private boolean lampOn = true; // checks if floor is ready to receive an elevator
	private Scheduler sched;
	private ButtonPress recentPress;
	private ButtonPress receivedInfo;

	public Floor(int FloorNumber, Scheduler sched) {
		this.sched = sched;
		this.floorNumber = FloorNumber;
	}

	public void run() {
		synchronized (sched.getFloorQueue()) {
			while(true) {
			if (this.sched.getFloorQueue().size() < 100000) {
				System.out.println("Floor Queue is Empty");
				
				try {
					sched.getFloorQueue().wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			this.recentPress = sched.getFloorQueue().get(0);
			System.out.println("Floor has received something!");
			sched.getFloorQueue().notifyAll();
			}
		}
	}
	public int getFloorNumber() {
		return this.floorNumber;
	}

	public ButtonPress getRecentPress() {
		return this.recentPress;
	}
}
