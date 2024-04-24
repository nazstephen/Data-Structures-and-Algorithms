//Node objects that make up the chains that make up the table
public class Node 
{
	//Contain value, key and address to the proceeding node
	private short val;
	private String key;
    public Node next;

	public Node(String key, short value) {
		this.key = key;
		this.val = value;
	}
    
	public boolean equals(Node n)  {
    	if(n.getKey() == this.key)
    		return true;
    	else
    		return false;
    }
    
    public String getKey()  {
        return key;
    }
    
    public short getVal() {
        return val;
    }
}



