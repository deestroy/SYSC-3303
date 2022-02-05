package src;

public class Elevator {
	
	private int currentFloor = 1; //All elevators start on the first floor.
	private boolean doorOpen = false;
	private boolean motorOn = false;;
	private int carId = 0;
	private ButtonPress recentPress;
	
	
	public Elevator(int carId) {
		this.carId = carId;
	}
	private void pressButton(int floorNumber) {
		this.recentPress = new ButtonPress(this);
		
	}
	
	public int getCurrentFloor() {
		return currentFloor;
	}
	public int getCarId() {
		return carId;
	}
}
