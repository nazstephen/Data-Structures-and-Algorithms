//Dictionary used for decompressing .zzz files
public class DecompDict
{
    public DChain[] hashTable;
    private int size;
    private int doubles;
    private int cap;

    //Initialize dictionary
    public DecompDict()
    {
        size = 0;
        doubles = 0;
        cap = 200;
        hashTable = new DChain[cap];
        for(short i = 0; i < 127; i++)
        {
            hashTable[i] = new DChain(i , Character.toString((char)(i)));
            size++;
        }
    }

    public boolean contains(short key) 
	{
        return hashTable[key] != null;
	}
    
	public String getVal(short key) 
	{
        return hashTable[key].getVal();
	}
    
        public int getDoubles() 
    {
        return doubles;
    }
    
	public void add(String value) 
	{
		if(size > .9 * cap) 
		{
			resize();
            add(value);
		}
        
        else 
        {
            size++;
			hashTable[size] = new DChain((short)size, value);
		}
	}

	public void resize() 
	{
        doubles = doubles + 1;
        cap = cap * 2;
		DChain[] temp = new DChain[cap];
		for(short i = 0; i < hashTable.length; i++)
		{
			temp[i] = hashTable[i];
		}
        
		hashTable = temp;
	}
}
