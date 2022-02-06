# SYSC 3303 Elevator Project

## Iteration 1
### Work Distribution
#### Dhriti Aravind
- Parsing the input txt file
- UML Diagram
- ReadMe
#### Mohammed Jemal
- UML Diargam
- Javadoc documenting
#### Aymaan Newaz
- Elevator.java
#### Mohamed Selim
- ReadMe
- Floor (button Events)
#### Marwan Zeyada

#### FloorSubsystem
The floorsubstystem manages the floor threads. It registers any button presses and converts them into the "instruction" text format. The instruction is then sent to the Scheduler,
which adds it to the list of scheduled tasks as necessary.#
##### Floors
A floor has fields about its number, an Up and  Down button,..

#### ElevatorSubsystem
The ElevatorSubsystem controls the Car threads. Whenever a Car is done with its task, the Elevator subsystem adds it to a "buffer" of free cars.

##### Cars
A car has information about its current task. This includes the floor it's heading to, the direction, and times needed for each part of the task.

#### Scheduler
The Scheduler continously checks the "buffer" for free cars, and then matches these cars with any unassigned "instructions".

It also receives any "instructions" from the FloorSubsystem and inputs them into the "Schedule.txt" file.
.
