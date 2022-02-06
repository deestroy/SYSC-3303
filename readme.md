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
The FloorSubsystem reads entries from a text file and converts them into a suitable format for the system. The instruction is then sent to the Scheduler, which adds it to the queue of scheduled tasks as necessary.

#### Floor
Has a button to input the direction the user wants to go. It also has a lamp that signals if the floor is ready to receive an Elevator

#### Elevator
An elevator has information about its current task. This includes the floor it's heading to, the direction, and time needed for the task. It receives instructions from the Scheduler, executes them, then sends a message to the Scheduler.

#### Scheduler
The Scheduler in this Iteration acts as a communication channel; it takes entries from the FloorSubsystem and delivers them to the Elevator. It also receives messages from the 
Elevator and delivers them to the Floor.

