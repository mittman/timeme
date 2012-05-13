/**
 * @name LoadFile
 * @author Team 0x00000001
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class LoadFile 
{	
	protected LoadFile(String pathToFile) throws IOException
	{
		String fileName = pathToFile+"";		
		boolean fileExists = new File(fileName).isFile();
		
		if(fileExists)
		{
			// Empty existing table
			while(Main.recentTasks.getItemCount() > 0)
			{
				Main.recentTasks.remove(0);
			}
			while(Main.allTasks.getItemCount() > 0)
			{
				Main.allTasks.remove(0);
			}
			
			// Load existing file
			try
			{
				  FileInputStream fileRead = new FileInputStream(fileName);
				  DataInputStream input = new DataInputStream(fileRead);
				  BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
				  
				  //Read File Line By Line
				  String thisLine = buffer.readLine();
				  while (thisLine != null)   
				  {				 
					  String[] col = thisLine.split("\\t");
					  String[] row = { col[0], col[1], col[2], col[3], col[4], col[5], col[6], col[7], col[8] };

					  TaskObject.addRow(0, row);
					  if(col[3].equals("+"))
					  {
						  String[] list = { col[1], col[2], col[4] };
						  TaskObject.addRecent(0, list);
					  }
					  //Main.maxTaskID = Integer.parseInt(col[4]);
					  thisLine = buffer.readLine();
				  }
				  TableListener.sort(true, 0);			  
				  input.close();
			}
			
			catch (Exception e)
			{
				System.err.println("Error: " + e.getMessage());			
			}
		}		
	}
}