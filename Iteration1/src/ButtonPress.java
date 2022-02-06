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
	
public boolean getButtonDirection() {
	return this.buttonDirection;
}
public int getFloorNumber() {
	return this.floorNumber;
}
public int getCarButton(){
  return this.carButton;
}
public int setCarButton(int newButton){
  this.carButton = newButton;
}
public String getPressTime() { //deestroyed can do date/time conversion
	return "";
}
}
