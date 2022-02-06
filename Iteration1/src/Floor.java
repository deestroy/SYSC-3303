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
public class Floor implements Runnable{
	private boolean buttonDirection; //True for up. False for down
	private int floorNumber;
	private boolean lampOn = true;			//checks if floor is ready to receive an elevator
  private Scheduler sched;
	private ButtonPress recentPress;
	private ButtonPress receivedInfo;
	
	
	public Floor(int FloorNumber) {
		this.floorNumber = FloorNumber;
    this.recentPress = new ButtonPress(buttonDirection, 0, floorNumber, LocalTime.now());
	}
	
	public void run() {
		synchronized(sched.getFloorQueue()){
      if (this.sched.getFloorQueue().size == 0){
        System.out.println("EMPTY");
        wait();
      }
      this.recentPress = sched.getFloorQueue().get(0);
      System.out.println("WORKED");
      notifyAll;
    }
  }
	
	/*public void pressUp() {
    this.buttonDirection = true;
		this.recentPress = pressButton(buttonDirection);
    this.scheduler.addTask(recentPress);
		
	}
	public void pressDown() {
    this.buttonDirection = false;
		this.recentPress = pressButton(buttonDirection);
		this.scheduler.addTask(recentPress);
	}
	
	private void pressButton(boolean buttonDirection) {
    this.recentPress = ButtonPress(buttonDirection, 0, this.floorNumber, LocalTime.now());
	}*/
	
	public int getFloorNumber() {
		return this.floorNumber;
	}
	public ButtonPress getRecentPress() {
		return this.recentPress;
	}
}
