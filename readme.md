# SYSC 3303 Elevator Project

## Iteration 2
### Work Distribution
#### Dhriti Aravind
- Parsing the input txt file
- Scheduler.java
- FloorSubsystem 
- Parsing
- UML Diagram
- README.txt
- ElevatorSubsystem.java

#### Mohammed Jemal
- UML Diagram
- Testing
- Javadoc documenting
- README.txt
- State Machine Diagram

#### Aymaan Newaz
- Elevator.java
- README.txt
- Set up instructions and testing
- Sequence Diagram
- State Machine Diagram

#### Mohamed Selim
- README.txt
- Floor (button Events)
- Elevator.java
- FloorSubsystem.java
- ButtonPress.java
- ElevatorSubsystem.java
- State Machine Diagram

#### Marwan Zeyada
- Testing
- FloorSubsystem.java
- Scheduler.java
- UML diagram
- README.txt
- ElevatorSubsystem.java
- State Machine Diagram

#### ButtonPress.java
ButtonPress is a container class for the messages that are passed back and forth through the components of the system. 

#### FloorSubsystem
The FloorSubsystem reads entries from a text file and converts them into a suitable format for the system. The instruction is then sent to the Scheduler, which adds it to the queue of scheduled tasks as necessary.

#### Floor
Has a button to input the direction the user wants to go. It also has a lamp that signals if the floor is ready to receive an Elevator

#### Elevator
An elevator has information about its current task. This includes the floor it's heading to, the direction, and time needed for the task. It receives instructions from the Scheduler, executes them, then sends a message to the Scheduler.

#### Scheduler
The Scheduler in this Iteration acts as a communication channel; it takes entries from the ElevatorSubsystem and delivers them to the Elevator. It also sends and receives messages from the ElevatorSubsystem about the status of Elevator.

#### ElevatorSubsystem.java
ElevatorSubsystem acts as a communication channel for Elevator.java and Scheduler.java; it takes instructions from Scheduler and sends them to Elevator for it to execute. Takes the instructions from Elevator and sends back to Scheduler

## Use Cases
Usecase: FloorButton
Primary Actor: Passanger
Basic Flow:

1. Passenger arrives and presses up or down button
2. If the elevator is in motion the appropriate up or down lamp is lit up
3. ButtonPress is communicated to the scheduler through FloorSubsystem
4. Scheduler checks if there is an elevator on the floor or if it has to send one
5. ElevatorSubsystem sends job to Elevator
6. Elevator notifies ElevatorSubsystem that elevator has been dispatched with estimated time
7. Up or Down lamp is turned on
8. Scheduler lets floor subsystem elevator is arriving along with estimated time
10. Floor turns off lamp
9. Floor waits for a new job

For example:
    Elevator is not in motion at floor 2 and all lamps are off
    Passenger is at floor one requests for an elevator by pushing up button
    Scheduler sees that the floor has passengers wanting to go to up
    Down lamp is turned on
    Sends elevator to first floor
    Elevator arrives to first floor
    Turns off the down lamp
    Elevator is moving up and up lamp is turned on
    
Use Case
ElevatorButton:

Basic Flow:

1- Passenger presses the button for the destination floor
2- ButtonPress is communicated to the Scheduler
3- Scheduler checks if there are other requests that can be fulfilled on the way (Default: NO)
4- Scheduler sends intructions to ElevatorSubsystem
5- ElevatorSubsystem sends job to Elevator
6- Elevator does job
7- Elevator asks for new job

Alternative flow:

RFS 3:
There exists an other "similar" request:
1- Scheduler reorganizes requests into 2*
2- Sends the first one to the elevator, schedules the next one for when elevator 
is free.
3- GOTO Basic Flow Step 5

* For example:
    Elevator at 1st floor, passenger is inside
    Passenger wants to go to 5th floor
    Scheduler sees that elevator was requested at floor 3

    Scheduler determines that this is a request that can be fulfilled on the way
    
    Scheduler makes 2 instructions:
    Move from 1st floor to 3rd floor
    Move from 3rd floor to 5th floor

## Time and speeds:
According to the Data provided in Project Iteration 0,

- #### The Average time the elevator spends per floor was calculated to be 9.51 sec
  This was found by taking the total time the elevator took to travel from the 1st floor to the 13th floor and dividing that by the number of floors.

- #### The Average speed of the elevator was found to be 0.4203 m/sec
  This was found by calculating the total distance travelled by the elevator in the provided dataset and dividing it by the time taken to travel this distance.

- #### The acceleration of the elevator was found to be 0.9 m/sec^2

- #### The maximum speed of the elevator in the recorded dataset was 2.3 m/sec

