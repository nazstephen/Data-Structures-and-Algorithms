/* Decompresses a .zzz file inputed by the user into a .txt file.
 * Uses hash tables to accomplish this task.
 *
 * Caesar Garcia
 * Ben Stephen
 *
 * April 15, 2019
 */

import java.util.Scanner;
import java.io.*;

public class Decompress extends DataInOut
{
    public static void main(String[] args) throws IOException
    {
        boolean goodFile = true;
        boolean badFile = false;
        String fileName = "";
        
        do
        {
            if(goodFile == true)
            {
                fileName = args[0];
                //fileName = "textfile.zzz";
            	goodFile = false;
            }
            
            else
            {
                Scanner in = new Scanner(System.in);
                System.out.println("Enter another file, or press X to exit program.");
                fileName = in.nextLine();
                if(fileName.equals("x") || fileName.equals("X"))
                {
                    System.exit(0);
                    
                }
            }
            
            DecompDict dict = new DecompDict();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName.substring(0, fileName.length()-4))))
            {
                DataInputStream in = initIS(fileName);
                //Calculate compression time
                long startTime = System.nanoTime();
                decompress(dict, in, bw);
                long time = System.nanoTime() - startTime;
                File dFile = new File(fileName.substring(0, fileName.length()-4));
                //Create log file
                log(dict, time, dFile);
                
            }
            
            catch (IOException e)
            {
            	
                System.out.println("Error!");
                
            }
        }
        
        while(badFile == false);
    }
	
    //Decompresses .txt file and outputs it to a .zzz file
	static void decompress(DecompDict dict, DataInputStream dis, BufferedWriter bw) throws IOException
	{
        short qChar = dis.readShort();
        bw.write(dict.getVal(qChar));
        bw.flush();
        short pChar = 0;
        try 
        {
            while(dis.available() > 0)
            {
            	pChar = dis.readShort();
                if(dict.contains(pChar)) 
                {
                    bw.write(dict.getVal(pChar));
                    dict.add(dict.getVal(qChar) + dict.getVal(pChar).charAt(0));
                }
                
                else 
                {
                    bw.write(dict.getVal(qChar) + dict.getVal(qChar).charAt(0));
                    dict.add(dict.getVal(qChar) + dict.getVal(qChar).charAt(0));
                }
                
                bw.flush();
                qChar = pChar;
                
                
            }
            
			bw.flush();
        }
        
        catch(EOFException e)
        {
            bw.flush();
            System.out.print(e);
        }
        
        bw.flush();
    }

	//Create log file
    public static void log(DecompDict dict, long time, File dFile) throws IOException
    {
        File logFile = new File(dFile.getName() + ".log");
        BufferedWriter out = new BufferedWriter(new FileWriter(logFile));
        out.write("Decopression for " + dFile.getName() + ".zzz\n");
        out.write("Decompression took " + time / 1000000000.0 + " seconds\n");
        out.write("The table was doubled " + dict.getDoubles() + " times\n");
        out.close();
    } 
}

