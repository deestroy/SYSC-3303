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
	private int floorNumber;
	private LocalTime currTime = LocalTime.now();
	
	
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
}
public ButtonPress(Elevator elevator) {
	
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
