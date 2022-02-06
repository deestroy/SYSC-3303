package src;
import java.time.LocalTime;
public class Elevator implements Runnable{
	private Scheduler sched;
	private int currentFloor = 1; //All elevators start on the first floor.
	private boolean doorOpen = false;
	private boolean motorOn = false;
	private int carId = 0;
	private ButtonPress recentPress;
	
	
	public Elevator(int carId, Scheduler scheduler) {
		this.carId = carId;
    this.Scheduler = scheduler;
	}
	private void pressButton(int floorNumber) {
    this.recentPress = ButtonPress((currentFloor - floorNumber >= 0),floorNumber, currentFloor, LocalTime.now());
    this.scheduler.addTask(recentPress);
	}
	
	public int getCurrentFloor() {
		return currentFloor;
	}
	public int getCarId() {
		return carId;
	}
  public void run(){
    synchronized (this.sched.getQueue()){
      if(this.sched.getQueue().size == 0){
        wait();
      }
      this.recentPress = this.sched.getQueue().get(0);
      this.sched.getQueue().remove(0);
      System.out.println("Receieved something");
      sleep(1000);
      this.sched.getFloorQueue().add(this.recentPress);
      System.out.println("Gave to floor queue");
      notifyAll();
    }
  }
}
