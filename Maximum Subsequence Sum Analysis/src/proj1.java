/* Implements four algorithms that solve for the max subsequence sum of an array of ints. 
 * Reads files that contains a single line of ints separated by white space characters.
 * Copies these ints into an array and asks the user which algorithm they would like to use.
 * Program executes the algorithms based on user input and displays the running times of each.
 * 
 * Benjamin Stephen
 * COMP 285-01
 * February 25, 2019
 */

import java.util.*;
import java.io.File;
import java.util.Scanner;

public class proj1 {
	public static void main(String[] args) {
		boolean loop = true;
		Scanner in = new Scanner(System.in);
		String file;
		int[] a;
		
		//Loop allows the user to run the program multiple times without quitting
		do {
			//Displays user-friendly menu
			System.out.println("What algorithm would you like to use?");
			System.out.println("1) Algorithm 1"); 
			System.out.println("2) Algorithm 2"); 
			System.out.println("3) Algorithm 3"); 
			System.out.println("4) Algorithm 4"); 
			System.out.println("5) Run all algorithms"); 
			System.out.println("6) Quit"); 
			
			System.out.println("\nEnter a number between 1 and 6: "); 
			int num = in.nextInt();
			
			//If user chooses algorithm 1
			if(num == 1) {
				System.out.println("\nYou chose Algorithm 1."); 
				System.out.println("What file do you want to read?: ");
				in.nextLine();
				file = in.nextLine();
				a = readFiles(file);
				algTime(1, MSS1(a));
			}
			
			//If user chooses algorithm 2
			if(num == 2) {
				System.out.println("\nYou chose Algorithm 2."); 
				System.out.println("What file do you want to read?: ");
				in.nextLine();
				file = in.nextLine();
				a = readFiles(file);
				algTime(2, MSS2(a));
			}
			
			//If user chooses algorithm 3
			if(num == 3) {
				System.out.println("\nYou chose Algorithm 3."); 
				System.out.println("What file do you want to read?: ");
				in.nextLine();
				file = in.nextLine();
				a = readFiles(file);
				algTime(3, MSS3(a));
			}
			
			//If user chooses algorithm 4
			if(num == 4) {
				System.out.println("\nYou chose Algorithm 4."); 
				System.out.println("What file do you want to read?: ");
				in.nextLine();
				file = in.nextLine();
				a = readFiles(file);
				algTime(4, MSS4(a));
			}
			
			//If user chooses all algorithms
			if(num == 5) {
				System.out.println("\nYou chose all algorithms."); 
				System.out.println("What file do you want to read?: ");
				in.nextLine();
				file = in.nextLine();
				a = readFiles(file);
				algTime(1, MSS1(a));
				algTime(2, MSS2(a));
				algTime(4, MSS3(a));
				algTime(4, MSS4(a));
			}
			
			//If user chooses to quit the program
			if(num == 6) {
				System.out.println("\nYou exited the program.");
				System.exit(0);
			}
		}
		
		while(loop);
	}
	
	//Method for the first algorithm
	public static int MSS1(int[] a) { 
		int n = a.length, maxSum = 0;
		for(int i = 0; i < n; i++)
			for(int j = i; j < n; j++) {
				int sum = 0;
				for (int k = i; k <= j; k++)
					sum += a[k];
				if (sum > maxSum)
					maxSum = sum;
			}
		
		return maxSum;
	}
	
	//Method for the second algorithm
	public static int MSS2(int[] a) { 
		int n = a.length, maxSum = 0;
		for(int i = 0; i < n; i++) { 
			int sum = 0;
			for(int j = i; j < n; j++) {  
				sum += a[j];
				if(sum > maxSum)
					maxSum = sum;
			}
		}
		
		return maxSum;
	}
	
	//Method for the third algorithm
	public static int MSS3(int[] a) {
		return maxSumRec(a, 0, (a.length - 1));
	}
	
	//Recursive method that is utilized by the third algorithm
	public static int maxSumRec(int[] a, int low, int high) { 
		if(low == high)
			if (a[low] > 0) 
				return a[low];
			else 
	        	return 0;
		
		int middle = (low + high) / 2;
		int maxLeftSum = maxSumRec(a, low, middle);
		int maxRightSum = maxSumRec(a, middle + 1, high);
		int maxLeftBoundSum = 0, maxRightBoundSum = 0, sum = 0; 
		for(int i = middle; i >= low; i--) { 
			sum += a[i];
			if (sum > maxLeftBoundSum)
				maxLeftBoundSum = sum;
		}
		
		sum = 0;
		for(int j = middle + 1; j <= high; j++) { 
			sum += a[j];
			if(sum > maxRightBoundSum)
	              maxRightBoundSum = sum;
	      }
		
		return Math.max(Math.max(maxLeftSum, maxRightSum), maxLeftBoundSum + maxRightBoundSum);
	}
	
	//Method for the fourth algorithm
	public static int MSS4(int[] a) { 
		int maxSum = 0, sum = 0;
		for (int j = 0; j < a.length; j++) { 
			sum += a[j];
	        if (sum > maxSum)
	        	maxSum = sum;
	        else if (sum < 0)
	        	sum = 0; 
	        }
		
		return maxSum;
	}
	
	//Method that reads the contents of the file entered by the user 
	//Loads the integers from the file into an array 
	public static int[] readFiles(String file) {
		try {
			File f = new File(file);
			Scanner s = new Scanner(f);
			int ctr = 0;
			while(s.hasNextInt()) {
				ctr++;
				s.nextInt();
			}
			
			int[] arr = new int[ctr];
			s.close();
			s = new Scanner(f);
			
			for(int i = 0; i < arr.length; i++)
				arr[i] = s.nextInt();
			return arr;
		}
		
		catch(Exception e) {
			return null;
		}
	}
	
	//Method that calls the method of the algorithm chosen by the user
	//Displays the running time of the respective algorithm
	public static void algTime(int n, int alg) {
		long milliseconds, ms1, ms2, nanoseconds, ns1, ns2;
		System.out.print("\nAlgorithm " + n + ": ");
		ms1 = System.currentTimeMillis();
		ns1 = System.nanoTime();
		int solution = alg;
		ns2 = System.nanoTime(); 
		ms2 = System.currentTimeMillis();
		System.out.println(solution);
		milliseconds = ms2 - ms1;
		System.out.println("Milliseconds: " + milliseconds);
		nanoseconds = ns2- ns1;
		System.out.println("Nanoseconds: " + nanoseconds + "\n");
	}
}