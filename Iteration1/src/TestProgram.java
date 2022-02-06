package src;

public class TestProgram {
	public static void main(String[] args) {
		Thread elevator1, floor1, sched1;
		FloorSubsystem floorsub = new FloorSubsystem();
		Scheduler sched = new Scheduler(floorsub);
		sched1 = new Thread(sched, "scheduler");
		elevator1 = new Thread(new Elevator(1, sched), "elevator");
		floor1 = new Thread(new Floor(5, sched), "floor");
		
		
		floorsub.parseData("C:\\Users\\zeyad\\Documents\\test.txt");
		sched1.start();
		elevator1.start();
		floor1.start();
	}
}
