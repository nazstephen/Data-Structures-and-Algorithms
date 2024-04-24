//Each index in the table contains a chain of linked lists
public class CChain
{
    private Node head;
	private int length;

    //Sets head to null and length of chain to 0
    public CChain()
    {
        head = null;
        length = 0;
    }
    
    public int getSize() 
    {
        return length;
    }
    
    public Node getHead() 
    {
        return head;
    }
    
    public boolean isEmpty() 
    {
    	if(head == null)
    		return true;
    	else
    		return false;
    }
    
    //Loops through the chain for a matching key and returns the value
    public short getVal(String key) 
    {
        Node n = head;
        short val;
        val = head.getVal();

        while (!n.getKey().equals(key)) 
        {
        	if(n.next != null)
        		n = n.next;
        	else 
        		break;
        }
        
        return n.getVal();
    }
    
    //Loops through the chain for a matching key
    public boolean contains(String key) 
    {
        Node n = head;
        while (n != null)
        {
            if (n.getKey().equals(key))
            {
                return true;
            }
            n = n.next;
        }
        
        return false;
    }

    //Inserts new node as the head
    public void insert(String key, short value)
    {
        if (head == null) 
        {
            head = new Node(key, value);
            head.next = null;
        }
        
        else 
        {
            Node n = new Node(key, value);
            n.next = head;
            head = n;
        }
        
        length = length + 1;
    }
}
