package src;
/* Client in system
 * Elevators will make calls to the Scheduler
 * - After Scheduler response, The Elevator will then send the data back to the Schedule
 * 
 * The  elevator  controller  will  have  to  be  multi-threaded  since  it  is  expected  to 
handle more than one car at a time.  
 * */
public class ElevatorSubsystem {
	private int initalFloor;
	private int finalFloor;
	private boolean doorOpen;
	private boolean motorOn;
	private int carId;
	
	
	public ElevatorSubsystem(int carId) {
		this.carId = carId;
		
		
	}
	
	
	
	

}
