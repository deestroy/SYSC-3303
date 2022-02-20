package src;

public class TestProgram {
	public static void main(String[] args) {
		Thread elevator1, sched1, elevsubsys1;
		FloorSubsystem floorsub = new FloorSubsystem();
		Scheduler sched = new Scheduler(floorsub);
		ElevatorSubsystem elevsubsys = new ElevatorSubsystem(sched);
		sched1 = new Thread(sched, "scheduler");
		elevator1 = new Thread(new Elevator(0, elevsubsys), "elevator");
		elevsubsys1 = new Thread(elevsubsys, "Elevator Subsystem");
		
		floorsub.parseData("C:\\Users\\moham\\Desktop\\SYSC-3303-iteration2\\Iteration1\\test.txt");
		sched1.start();
		elevator1.start();
		elevsubsys1.start();
	}
}
