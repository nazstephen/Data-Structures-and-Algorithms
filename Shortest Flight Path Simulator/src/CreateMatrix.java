
//Creates a class that creates the matrix from the two files
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class CreateMatrix {
	static Scanner input = new Scanner(System.in);
	
	//Adds cities from file as Nodes into an array
	public static Node[] cityArray() throws IOException, FileNotFoundException {
		//Asks the user to input cityFile name
		System.out.println("Enter city file: ");
		String cityFile = input.nextLine();
		
		//FileReader object used for finding the length of the array
		FileReader inCityFile = new FileReader(cityFile);
		
		//FileReader object used for inserting nodes into the array
		FileReader inCityFile2 = new FileReader(cityFile);
		
		//Declares and assigns an array with a size
		BufferedReader br = new BufferedReader(inCityFile);
		String loc = "";
		int length = 0; 
		while((loc = br.readLine()) != null) { 
			length++;	
		}	
		
		Node[] ar = new Node[length];
		
		//Inserts nodes into the array
		BufferedReader br2 = new BufferedReader(inCityFile2);
		String loc2 = "";
		int index = 0; 
		while((loc2 = br2.readLine()) != null) { 
			Flight city = new Flight(loc2);
			Node n = new Node(city);
			ar[index] = n;
			index++;
		}
		
		return ar;
	}
	
	//Creates and array of LinkedLists
	public static Node[] LinkedList(Node[] ar) throws IOException, FileNotFoundException {
		System.out.println("Enter flight file: ");
		String flightFile = input.nextLine();
		FileReader inFlightFile = new FileReader(flightFile);
		BufferedReader br3 = new BufferedReader(inFlightFile);
		StringTokenizer st;
		String line = "";
		int count;
		String loc , dest , cost;
		int cst;
		
		while((line = br3.readLine()) != null) { 
			st = new StringTokenizer(line, ",");
			count = 0;
			loc = "";
			dest = "";
			cost = "";
			cst = 0;
			
			while(st.hasMoreTokens()) {
				count++;
				
				if(count == 1)
					loc = st.nextToken();

				else if(count == 2)
			        dest = st.nextToken();
				
				else if(count == 3) {
					cost = st.nextToken();
					cst = Integer.parseInt(cost);
					Flight f = new Flight(loc, dest, cst);
					Node n = new Node(f);
					Node curr;
					for(int i = 0; i < ar.length; i++) {
						if((ar[i].getFlight().getLoc()).equals(n.getFlight().getLoc())) {
							curr = ar[i];
							while(curr.getNext() != null)
								curr = curr.getNext();
							curr.setNext(n);
							curr = null;
						}
					}
					
					count = 0;
				}
			}
		}
		
		return ar;
	}
}
