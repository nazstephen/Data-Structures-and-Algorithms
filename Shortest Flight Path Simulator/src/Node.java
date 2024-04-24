
//Creates a Node class
public class Node {
	private Flight flight;
	private Node next;
	
	public Node() {
		this.flight = null;
		this.next = null;
	}
	
	public Node(Flight flight) {
		this.flight = flight;
		this.next = null;
	}
	
	public Node(Flight flight, Node newNode) {
		this.flight = flight;
		this.next = newNode;
	}
	
	public Flight getFlight() {
		return flight;
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setFlight(Flight newFlight) {
		this.flight = newFlight;
	}
	
	public void setNext(Node newNode) {
		this.next = newNode;
	}
}
