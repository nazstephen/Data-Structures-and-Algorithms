
//Creates a Stack class
import java.util.EmptyStackException;

public class Stack {
	private Node top;
	private int size;
	
	public Stack() {
		top = null;
		size = 0;
	}
	
	public boolean StackIsEmpty() {
		return(top == null);
	}
	
	public int size() {
		return size;
	}
	
	public void push(Flight newFlight) {
		top = new Node(newFlight, top);
		size++;
	}
	
	public Object pop()
		throws EmptyStackException {
		
		if(!StackIsEmpty()) {
			Object r = top.getFlight();
			Node cur = top;
			top = top.getNext();
			cur = null;
			size--;
			return r;
		}
		
		else
			throw new EmptyStackException();
	}
	
	public Object peek()
		throws EmptyStackException {
		
		if(!StackIsEmpty())
			return top.getFlight();
		else 
			throw new EmptyStackException();
	}
}
