package src;

import java.util.ArrayList;

/*
 *  Scheduler will reply to the Elevator when there is work to be done
 *  - After data is sent from to the elevator, it will forward the Data to the floor
 *  
 *  Iteration 1:
 *  Scheduler is only being used as a communication channel from the Floor thread to the Elevator thread and back again
 */

/*
 * Communicates between ElevatorSubsystem and FloorSubsystem
 * - Scheduler will reply to the Elevator when there is work to be done
 * - After data is sent from to the elevator, it will forward the Data to the floor
 */
public class Scheduler implements Runnable{
	
	private ArrayList<Floor> floorData;
	
	public Scheduler() {
		
	}
	
	@Override 
	public void run() {
		
	}
	

}
