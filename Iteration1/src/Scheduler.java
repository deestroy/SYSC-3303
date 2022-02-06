package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

/*
 * Communicates between ElevatorSubsystem and FloorSubsystem
 * - Scheduler will reply to the Elevator when there is work to be done
 * - After data is sent from to the elevator, it will forward the Data to the floor
 */
public class Scheduler implements Runnable{
	
private ArrayList<ButtonPress> queue;
private ArrayList<ButtonPress> floorQueue;
private FloorSubsystem floorsub;
//	private boolean elevatorReady; //elevator ready to receive requests
//	private boolean elevatorTask; //If the elevator is taking data from the File
//	private boolean floorTask; //If the floor is taking new data
//	// private HashMap<Integer, ArrayList<ButtonPress>> scheduledData = new HashMap<Integer, ArrayList<ButtonPress>>(); // car ID and userda
//	
//	private ArrayList<Elevator> elevators;
//	private ArrayList<Floor> floors;
//	
//	

/*Constructor for Scheduler
@param elevators List of elevators
@param floors List of floors
*/
public Scheduler(FloorSubsytem floorsub) {
  this.floorsub = floorsub;
}

public Scheduler(FloorSubsytem floorsub, ArrayList<ButtonPress> queue) {
  this.floorsub = floorsub;
  this.queue = queue;
}
/**
	 * Find a command in the queue
	 * @param task queried
	 */
public synchronized void getQueueValue() {
		queue.get(0);
		notifyAll();
}
public ArrayList<ButtonPress> getQueue(){
  return this.queue;
}
public ArrayList<ButtonPress> getFloorQueue(){
  return this.floorQueue;
}
/**
	 * Add a new command to the queue
	 * @param task added
	 */
public synchronized void addToQueue(ButtonPress btn) {
		queue.add(btn);
		notifyAll();
	}
//	
////	/**
////	 * Finished an elevator task
////	 */
////	public void finishTask() {
////		userData.remove(0);
////	}
////	
////	/**
////	 * Add an elevator task
////	 */
////	public void addTask(ButtonPress bu) {
////		userData.add(bu);	
////	}
	
	
@Override 
public void run() {
  synchronized (this.floorsub.getInfo()){
    if (this.floorsub.getInfo().size == 0){
      wait();
    }
    queue.add(this.floorsub.getInfo().get(0));
    this.floorsub.getInfo().remove(0);
    System.out.println("took from floorsub");
    notifyAll();
    }
	}
}
