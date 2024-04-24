
/* Program that takes a cityFile and a flightFile inputed by the user 
 * and creates an adjacency list with the information. Allows the user
 * find if there is an existing flight path from one city to another 
 * and returns the flights the user needs to take and how expensive the 
 * total trip is.
 * 
 * Ben Stephen
 * Caesar Garcia
 * 
 * May 6, 2018
 */

import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class proj3 {
	public static void main(String [] args) throws IOException {
		Scanner in = new Scanner(System.in);
		Node[] cityArray, matrix;
		boolean loop = true;
		
		//Lets the program run multiple times
		do {
			try {
				Stack stack = new Stack();
				cityArray = CreateMatrix.cityArray();
				matrix = CreateMatrix.LinkedList(cityArray);
				System.out.println("What city are you traveling from?");
				String location = in.nextLine();
				System.out.println("Where would you like to go?");
				String destination = in.nextLine();
				System.out.println("Request is to fly from " + location + 
									" to " + destination + ".");
				
				//Declares an ArrayList of cities we have visited;
				ArrayList hasSeen = new ArrayList(100);
				
				//Checks to see if city entered has any flights going from it
				for(int i = 0; i < matrix.length; i++) {
					if(matrix[i].getFlight().getLoc().equals(location)) {
						if(matrix[i].getNext() == null) {
							System.out.println("Sorry USAir does not fly from " + location + 
												" to " + destination + ".");
							continue;
						}
					}
				}
				
				//Calls traversal method to find flight path
				int end = traversal(location, destination, matrix, stack, hasSeen);
				switch(end) {
					case 1:
						Flight[] route = new Flight[stack.size()];
						for(int i = stack.size()-1; i >= 0; i--) {
							route[i] = (Flight)stack.pop();
						}
						
						int total = 0;
						for(Flight f: route) {
							System.out.println(f);
							total += f.getCost();
						}
						
						System.out.println("Total Cost ............. $" + total);
						break;
					case -1:
						System.out.println("Sorry USAir does not fly from " + location + " to " + destination);
						break;
				}
			} 
			
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
			
			System.out.println("Press Enter to check another flight or 'Q' to quit.");
			String option = in.nextLine();
			if(option.toLowerCase().equals("q")) {
				loop = false;
			}
		}
		
		while(loop == true);
	}
	
	//Traversal method that finds path from one city to another
	public static int traversal(String location, String destination, Node[] ar, Stack stack, ArrayList hasSeen) {
		//add current city into ArrayList
		hasSeen.add(location);
		Node temp = null;
		//Searches through the array for matching city
		for(int i = 0; i < ar.length; i++) {
			//Gets flight path Node of the city
			if(ar[i].getFlight().getLoc().equals(location)) {		
				temp = ar[i].getNext();
				if(temp == null)
					return 0;
				
				else {
					//While there are more flight paths for the current city
					while(temp != null) {
						//Push Node into stack of flight paths
						stack.push(temp.getFlight());
						if(hasSeen.contains(temp.getFlight().getDest())) {
							temp = temp.getNext();
							//If not correct path, pop it from stack
							stack.pop();
							continue;
						}
						
						//If the flight leads to the destination
						if(temp.getFlight().getDest().equals(destination))
							return 1;
							
						else {
							if(traversal(temp.getFlight().getDest(), destination, ar, stack, hasSeen) == 1)
								return 1;
							//If not correct path, pop it from stack
							stack.pop();
							
						}
						
						temp = temp.getNext();
					}
				}
			}
		}
		
		return -1;
	}
}
