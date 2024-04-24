//Initializes the file input/output stream for both
//compression and decompression algorithms
import java.io.*;

public abstract class DataInOut
{
	//Initializes the input stream
    public static DataInputStream initIS(String fileName)
    {
        FileInputStream fis = null;
        DataInputStream dis = null;
        
        try 
        {
            fis = new FileInputStream(fileName);
            dis = new DataInputStream(fis);
        }
        
        catch (IOException e)
        {
            System.out.println("File cannot be found!");
        }
        
        return dis;
    }
	
	//Initializes the output stream
    public static DataOutputStream initOS(String fileName)
    {
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try 
        {
            fos = new FileOutputStream(fileName);
            dos = new DataOutputStream(fos);
        }
        
        catch (IOException e)
        {
            System.out.println("File cannot be found!");
        }
        
        return dos;
    }
}
