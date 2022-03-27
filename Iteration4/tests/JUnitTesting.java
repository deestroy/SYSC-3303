import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class JUnitTesting extends TestCase {
	/*
	 * FloorSubsystem floorsub; Scheduler sched; ElevatorSubsystem elevsubsys;
	 * ButtonPress button;
	 */
	FloorSubsystem floorsub = new FloorSubsystem();
	Scheduler sched = new Scheduler(floorsub);
	ElevatorSubsystem elevsubsys = new ElevatorSubsystem(sched);
	Floor flr = new Floor(1);
	Elevator elev = new Elevator(0, elevsubsys);
	ButtonPress button = new ButtonPress(true, 0, 1, LocalTime.now());

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testClasses() {
		System.out.println("Testing objects are not null");
		assertNotNull(floorsub);
		assertNotNull(sched);
		assertNotNull(elevsubsys);
		assertNotNull(flr);
		assertNotNull(elev);
		assertNotNull(button);

		System.out.println("Testing object types");
		assertTrue(floorsub instanceof FloorSubsystem);
		assertTrue(sched instanceof Scheduler);
		assertTrue(elevsubsys instanceof ElevatorSubsystem);
		assertTrue(flr instanceof Floor);
		assertTrue(elev instanceof Elevator);
		assertTrue(button instanceof ButtonPress);

		System.out.println("Testing floor creation");
		assertEquals(1, flr.getFloorNumber());
		assertNotSame(2, flr.getFloorNumber());

		System.out.println("Testing Elevator creation");
		assertEquals(2, elev.getCurrentFloor());
		assertNotSame(100, elev.getCurrentFloor());
		assertEquals(0, elev.getCarId());
		assertEquals(2000, elev.timeElevator(1, 2));

		System.out.println("Testing Button creation");
		assertTrue(button.getButtonDirection());
		assertEquals(1, button.getFloorNumber());
		assertEquals(0, button.getCarButton());

		System.out.println("Testing FloorSubsystem creation");
		assertTrue(floorsub.getInfo() instanceof ArrayList);

		System.out.println("Testing ElevatorSubsystem creation");
		assertTrue(elevsubsys.getTaskList() instanceof ArrayList);

		System.out.println("Testing Scheduler creation");
		assertTrue(sched.getQueue() instanceof ArrayList);
		assertTrue(sched.getFloorQueue() instanceof ArrayList);
	}

}
