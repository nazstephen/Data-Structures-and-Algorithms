
//Creates a Flight class
public class Flight {
	private String location;
	private String destination;
	private int cost;
	
	public Flight()
	{
		this.location = null;
		this.destination = null;
		this.cost = 0;
	}
	
	public Flight(String loc, String dest, int cst)
	{
		this.location = loc;
		this.destination = dest;
		this.cost = cst;
	}
	
	public Flight(String loc) {
		this.location = loc;
	}
	
	public String getLoc() {
		return location;
	}
	
	public String getDest() {
		return destination;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setLoc(String newLoc) {
		this.location = newLoc;
	}
	
	public void setDest(String newDest) {
		this.destination = newDest;
	}
	
	public void setCost(int newCost) {
		this.cost = newCost;
	}
	
	public String toString() {
		String s = "Flight from " + location + " to " + destination + "\tCost: $" + cost;
		return s;
	}
}
