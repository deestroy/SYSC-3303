package src;
/* Client in system
 * THIS CLASS IS A PLACEHOLDER FOR FUTURE MILESTONES.
 * Elevators will make calls to the Scheduler
 * - After Scheduler response, The Elevator will then send the data back to the Schedule
 * 
 * The  elevator  controller  will  have  to  be  multi-threaded  since  it  is  expected  to 
handle more than one car at a time.  

 * */
public class ElevatorSubsystem implements Runnable{
	private Elevator[] cars = Elevator[1];
  private Scheduler sched;
	private ArrayList<ButtonPress> taskList = new ArrayList<ButtonPress>();
  private ArrayList<ButtonPress> responseList = new ArrayList<ButtonPress>();
	
	public ElevatorSubsystem(Scheduler sched) {
    this.sched = sched;
		this.cars[0] = new Elevator(0, this);
	}
	
  public void run(){
    synchronized (this.sched.getQueue() {
			while(true) {
			if (this.sched.getQueue().size() == 0) {
				System.out.println("Scheduler queue is Empty " + LocalTime.now());
				try {
					this.sched.getQueue().wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			this.recentPress = this.sched.getQueue().get(0);
			this.sched.getQueue().remove(0);
			System.out.println("ElevatorSubsystem receieved something!");
      );
  }
	
	public ArrayList<ButtonPress> getTaskList(){
    return this.taskList;
  }
  /**
	* Adds button presses to the floor queue.
	*/
	public void addResponseList(ButtonPress buttonpress) {
		synchronized(this.responseList) {
		this.responseList.add(buttonpress);
		this.responseList.notifyAll();
	}
	}
	

}
