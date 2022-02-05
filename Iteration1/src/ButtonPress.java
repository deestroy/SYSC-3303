/**
 * 
 */
package src;
import java.time.LocalTime;
/**
 * @author moham
 *
 */
public abstract class ButtonPress {
	private boolean buttonDirection;
	private int floorNumber;
	private LocalTime currTime = LocalTime.now();
	
	
	
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
