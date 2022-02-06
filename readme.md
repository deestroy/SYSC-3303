# SYSC 3303 Elevator Project

## Iteration 1
### Work Distribution
#### Dhriti Aravind
- Parsing the input txt file
- Scheduler.java
- FloorSubsystem - Parsing
- UML Diagram
- README.txt

#### Mohammed Jemal
- UML Diagram
- Testing
- Javadoc documenting
- README.txt

#### Aymaan Newaz
- Elevator.java
- README.txt
- Set up instructions and testing
- Sequence Diagram

#### Mohamed Selim
- README.txt
- Floor (button Events)
- Cars.java
- FloorSubsystem.java
- ButtonPress.java

#### Marwan Zeyada
- Testing
- FloorSubsystem.java
- Scheduler.java
- UML diagram
- README.txt

#### ButtonPress.java
ButtonPress is a container class for the messages that are passed back and forth through the components of the system. 

#### FloorSubsystem
The floorsubstystem manages the floor threads. It registers any button presses and converts them into the "instruction" text format. The instruction is then sent to the Scheduler,
which adds it to the list of scheduled tasks as necessary.#

#### Floors
Has a button to input the direction the user wants to go to. It will send a message to Elevator.java when the corresponding button is pressed.

#### Cars
A car has information about its current task. This includes the floor it's heading to, the direction, and times needed for each part of the task.

#### Scheduler
The Scheduler continously checks the "buffer" for free cars, and then matches these cars with any unassigned "instructions".
It also receives any "instructions" from the FloorSubsystem and inputs them into the "Schedule.txt" file.

