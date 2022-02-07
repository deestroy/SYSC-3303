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
- Elevator.java
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

## Time and speeds:
According to the Data provided in Project Iteration 0,

- #### The Average time the elevator spends per floor was calculated to be 9.51 sec
  This was found by taking the total time the elevator took to travel from the 1st floor to the 13th floor and dividing that by the number of floors.

- #### The Average speed of the elevator was found to be 0.4203 m/sec
  This was found by calculating the total distance travelled by the elevator in the provided dataset and dividing it by the time taken to travel this distance.

- #### The acceleration of the elevator was found to be 0.9 m/sec^2

- #### The maximum speed of the elevator in the recorded dataset was 2.3 m/sec
