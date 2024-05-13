# Shortest Flight Path Simulator
This is a Java Program that has 6 classes Proj3.java, Node.java, Stack.java, ArratList.java, Flight.java, and CreateMatrix.java. Proj3.java reads in user input, declares all necessary data structures, displays the menu and results, and houses the main traversal method responsible for traversing the adjacency list. 

## Flight.java
Flight.java allows for the creation of a flight object with location, destination, and cost. 

## Stack.java
Stack.java is our chosen data structure allowing the program to “remember” the correct flight path taken to arrive at the destination. The ArrayList class is used to keep track of nodes that have been seen during traversal. The adjacency list itself is created using CreateMatrix.java.

## CreateMatrix.java
In CreateMatrix.java, the list of cities is read into the program and an array is created. Next, flight information is read into respective flight objects, placed into nodes, and fed into the array position where its location matches the location already placed into the city array. These nodes are added into their respective positions until an adjacency list representation of the flight paths is achieved. 

## Proj3.java
In Proj3.java, following the creation of the adjacency list, the traversal method is called. This method takes in a location, destination, an array to track what nodes have already been visited, and a stack to track the correct path. This method begins by scanning the list for the given location. Each node arrived at is added to the stack and the hasSeen array. It then traverses the linked list until it arrives at the next node in the list so long as it has not already been seen. If it has been seen, it is skipped over. Otherwise, the method is recursively called and searches the city array for that flight’s destination. It then begins the process again with that respective list. If the algorithm arrives at a null value and it is not the destination, it returns to the last visited node by popping the stack. Proj3.java then reads from the stack to display the flight patterns and adds up the total cost.
