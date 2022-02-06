package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Scanner;
/*
 * Basic Data about current status of floor
 * Takes the information in table and processes it
 */

/**
 * @author Mohamed Selim
 */
public class Floor implements Runnable{
	private boolean upPressed = false;
	private boolean downPressed = false;	//direction of travel
	private int floorNumber;
	private boolean lampOn = true;			//checks if floor is ready to receive an elevator
	private ButtonPress recentPress;
	private ButtonPress receivedInfo;
	
	
	public Floor(int FloorNumber) {
		this.floorNumber = FloorNumber;
	}
	
	public void run() {
		
	}
	
	public void pressUp() {
		this.upPressed = true;
		this.recentPress = sendButtonPress(0);
		this.upPressed = false;
		
	}
	public void pressDown() {
		this.downPressed = true;
		this.recentPress = sendButtonPress(1);
		this.downPressed = false;
	}

	
	private ButtonPress sendButtonPress(int direction) {
		ButtonPress lastPress = new ButtonPress(this, direction);
		return lastPress;
	}
	
	public int getFloorNumber() {
		return this.floorNumber;
	}
	public ButtonPress getRecentPress() {
		return this.recentPress;
	}
}
