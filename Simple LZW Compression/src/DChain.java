//Each index in the decompress dictionary contains a chain of linked lists
public class DChain
{
    private short key;
    private String value;
    
    DChain(short key, String value)
    {
        this.key = key;
        this.value = value;
    }
    
    public void setKey(short key)
    {
        this.key = key;
    }
    
    public void setVal(String value)
    {
        this.value = value;
    }
    
    public short getKey()
    {
        return key;
    }
    
    public String getVal()
    {
        return value;
    }
}


