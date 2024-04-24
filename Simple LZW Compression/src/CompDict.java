//Dictionary used for compressing .txt files
public class CompDict
{
    public short size;
    public int tableSize;
    public CChain[] hashTable;
    public int numRehash;
    
    public long [] primeNums =
        {127, 283, 419, 877, 1663, 3001,
         4447, 6067, 10009, 11839,12043};

    //Constructor for initializing hash table w/ ASCII values
    public CompDict(int ts) {
        numRehash = 0;
        if (ts < 127) 
        {
            System.out.println("Table size must be at least 127 indeces long.");
            System.exit(7);
        }
        
        int hash;
        size = 0;
        tableSize = ts;
        hashTable = new CChain[ts];
        
        for (short i = 0; i < ts; i++) 
        {
            hashTable[i] = new CChain();
        }

        for (short i = 0; i < 127; i++) 
        {
            String key = Character.toString((char)(i));
            hash = hashFunc(key);
            hashTable[hash].insert(key, i);
            size++;
        }
    }
    
    //Constructror for rehashing
    public CompDict(CompDict old)
    {
        this.size = old.size;
        this.numRehash = old.numRehash + 1;
		this.tableSize = old.nextPrime();
        this.hashTable = new CChain[this.tableSize];
        
        //Inserts chains of linked lists into each index of new table
        for (int i = 0; i < this.tableSize; i++) 
        {
            this.hashTable[i] = new CChain();
        }

        //Places nodes from previous table into new table
        for (int i = 0; i < old.getTableSize(); i++)
        {
            Node n = old.hashTable[i].getHead();
            while (n != null) 
            {
                this.hashTable[hashFunc(n.getKey())].insert(n.getKey(), n.getVal());
                size++; 
                n = n.next;
            }
        }
    }

    public boolean contains(String key)
    {
        return hashTable[hashFunc(key)].contains(key);
    }

	public void add(String key)
	{
        size++;
		if(0 > size)
		{
			System.exit(1);
		}
        
        hashTable[hashFunc(key)].insert(key, size);
	}

    public short getVal(String key) 
    {
        return hashTable[hashFunc(key)].getVal(key);
    }

    public int getTableSize() 
    {
        return tableSize;
    }
	
	public int getSize() 
	{
		return size;
	}

    //Hash function that takes a key value and adds it to its corresponding ASCII value
	public int hashFunc(String key)
	{
		int hash = 0;
		for(int i = 0; i < key.length(); i++) 
		{
			hash += (int)key.charAt(i);
		}
        
		int index = hash % tableSize;
		return index;
		
	}
    
	//Returns the next prime number when resizing the table
	public int nextPrime() 
	{
        for (int j = 0; j < primeNums.length; j++)
        {
            if (primeNums[j] == tableSize)
            {
                if (j == primeNums.length - 1)
                {
                    break;
                }
                
                return (int)primeNums[j + 1];
            }
        }
        
        return this.getTableSize() * 2 + 1;
	}
}
