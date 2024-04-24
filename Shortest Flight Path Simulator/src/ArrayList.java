
//Creates an ArrayList
public class ArrayList{
	private String[] list;
	private int index;
	
	public ArrayList(int size){
		list = new String[size];
		index = 0;
	}
	
	public ArrayList(){
		this(100);
	}
	
	public void add(String n) {
		list[index++] = n;
	}
	
	public String[] getArray() {
		return list;
	}
	
	public boolean contains(String s) {
		for(int i = 0; i < index; i++) {
			if(list[i].equals(s))
				return true;
			
		}
		return false;
	}
}