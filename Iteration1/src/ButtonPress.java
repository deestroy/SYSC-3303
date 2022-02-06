/**
 * 
 */
package src;
import java.time.LocalTime;
/**
 * @author moham
 *
 */
public class ButtonPress {
	private boolean buttonDirection;
	private int carButton;
	private int floorNumber;
	private LocalTime currTime = LocalTime.now();
	
public ButtonPress(boolean buttonDirection, int carButton, int floorNumber, LocalTime buttonTime) {
	this.buttonDirection = buttonDirection;
	this.carButton = carButton;
	this.floorNumber = floorNumber;
	this.LocalTime = buttonTime;	
}
public ButtonPress(Floor floor, int direction) {
	this.floorNumber = floor.getFloorNumber();
	switch (direction){
		case 0:{
			this.buttonDirection = true;
			break;
		}
		case 1:{
			this.buttonDirection = false;
			break;
		}
	}
	this.carButton = false;
}
public ButtonPress(Elevator elevator) {
	this.carButton = true;
	this.floorNumber = elevator.getCurrentFloor();
	this.buttonDirection = ((elevator.getCurrentFloor() - this.floorNumber) >= 0);
}
	
public boolean getButtonDirection() {
	return this.buttonDirection;
}
public int getFloorNumber() {
	return this.floorNumber;
}
public String getPressTime() { //deestroyed can do date/time conversion
	return "";
}
}
