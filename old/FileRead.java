import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class FileRead 
{
	public static void main(String args[]) throws IOException
	{
		String fileName = "/dev/shm/textfile.txt";
		boolean configExists = new File(fileName).isFile();
		
		if(!configExists)
		{
			//FileWriter fwrite = new FileWriter(fileName,true); // Append
			FileWriter fwrite = new FileWriter(fileName);
			BufferedWriter out = new BufferedWriter(fwrite);
			out.write("Hello world\n");
			out.write("The quick brown fox\n");
			out.close();
		}
		
		try
		{
			  FileInputStream fstream = new FileInputStream(fileName);
			  
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   
			  {
				  System.out.println (strLine);
			  }
			  
			  //Close the input stream
			  in.close();
		}
		
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());			
		}
	}
}
