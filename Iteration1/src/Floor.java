package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Scanner;
/*
 * Basic Data about current status of floor
 * Takes the information in table and processes it
 * Represents a floor of a building.
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

	/**
	* Constructor for the floor class.
	*/
	public Floor(int FloorNumber, Scheduler sched) {
		this.sched = sched;
		this.floorNumber = FloorNumber;
	}

	/**
	* This is the section for running with threads. 
	*/
	public void run() {
		synchronized (sched.getFloorQueue()) {
			while(true) {
			if (this.sched.getFloorQueue().size() < 100000) {
				System.out.println("Floor Queue is Empty " + LocalTime.now());
				
				try {
					sched.getFloorQueue().wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			this.recentPress = sched.getFloorQueue().get(0);
			System.out.println("Floor has received something! " + LocalTime.now());
			sched.getFloorQueue().notifyAll();
			}
		}
	}
	
	/**
	* Returns the number of the floor.
	*/
	public int getFloorNumber() {
		return this.floorNumber;
	}


  /**
  *
  */
  public void changeLampStatus() {
  }

	/**
	* Indicates the button that was most recently pressed.
	*/
	public ButtonPress getRecentPress() {
		return this.recentPress;
	}
}
