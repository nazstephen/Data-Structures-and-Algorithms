/* Compresses a .txt file inputed by the user into a binary coded file.
* Uses hash tables to accomplish this task.
*
* Caesar Garcia
* Ben Stephen
*
* April 15, 2019
*/

import java.io.*;
import java.util.Scanner;

public class Compress extends DataInOut
{
	public static void main(String[] args)
	{
        
       	if(new File(args[0]).exists())
       	{
       		compress(args[0]);
       	}
        
        else
        {
            System.out.println("Cannot find file!");
        }
        
        boolean EOFName = false;
        Scanner in = new Scanner(System.in);

        do
        {
            String nextFile = "";
            System.out.println("Enter another file, or press X to exit program.");
            nextFile = in.nextLine();
            
            if(nextFile.equals("x") || nextFile.equals("X"))
            {
                EOFName = true;
            }
            
            else
            {
                File temp = new File(nextFile);
                if(new File(nextFile).exists() == false)
                {
                    continue;
                }
                
                compress(nextFile);
            }
        }
        
        while(EOFName == false);
        System.exit(0);
	}
    
    public static void compress(String file)
    {
		CompDict dict = new CompDict(127);
		String cFile = createFile(file);
		DataInputStream dis = initIS(file);
		DataOutputStream dos = initOS(cFile);

		try 
		{
			//Calculate compression time
			long startTime = System.nanoTime();
			dict = compress(dis, dos, dict);
			long time = System.nanoTime() - startTime;
			//Create log file
            log(time, new File(file), new File(cFile), dict);
		}
        
        catch(IOException e)
		{
			System.exit(1);
		}
    }
    
    //Compresses .txt file and outputs it to a .zzz file
    static CompDict compress(DataInputStream dis, DataOutputStream dos, CompDict dict) throws IOException
	{
		String sKey = "";
		try 
		{
			while (dis.available() > 0) 
			{
				char cKey = (char)dis.readByte();
				if(dict.getTableSize() * 1.5 < dict.getSize())
				{
					CompDict tempDict = new CompDict(dict);
					dict = tempDict;
					dict.numRehash = dict.numRehash + 1;
					
				}
                
				String full = sKey + cKey;
				if(dict.contains(full))
				{
					sKey = full;
				}
                
				else 
				{
					dos.writeShort(dict.getVal(sKey));
					dict.add(full);
					sKey = "" + cKey;
				}
			}
            
            dos.flush();
        }
        
        catch(EOFException e)
		{
			dos.writeShort(dict.getVal(sKey));
			dos.flush();
		}
        
		return dict;
	}

	//Create compressed file
	public static String createFile(String file)
	{
		String cFile = file + ".zzz";
		return cFile;
	}
    
	//Create log file
    public static void log(long time, File file, File cFile, CompDict dict) throws IOException
    {
    	String logFile = file.getName() + ".zzz.log";
        File log = new File(logFile);
        
        if(log.createNewFile() == false)
        {
            System.out.println("Already compressed!");
        }

        FileWriter logWrite = new FileWriter(log);
        BufferedWriter out = new BufferedWriter(logWrite);
      
        //Display stats of compression
        out.write("Compression of " + file.getName() + "\n");
        out.write("Compressed from " + file.length() / 1000.0 + " Kilobytes to " + cFile.length() / 1000.0 + " Kilobytes\n" );
        out.write("Compression took " + time / 1000000000.0 + " seconds\n");
        
        //Calculates average number of elements in linked lists
        int totalElements = 0;
        for(int i = 0; i < dict.getTableSize(); i++)
        {
            totalElements = totalElements + dict.hashTable[i].getSize();
        }
        
        out.write("The average linked list is " + 1.0 * totalElements / dict.getTableSize() + " elements long\n");
        
        //Find and display size of index w/ longest linked list
        int index = 0;
        int longest = 0;
        for(int i = 0; i < dict.getTableSize(); i++)
        {
            if(dict.hashTable[i].getSize() > longest)
            {
                longest = dict.hashTable[i].getSize();
                index = i;
            }
        }
        
        out.write("The longest linked list contains " + longest + " elements\n");
        out.write("The dictionary contains " + dict.size + " total entries\n");
        out.write("The table was rehashed " + dict.numRehash + " times\n");
        out.close();
	}
}
