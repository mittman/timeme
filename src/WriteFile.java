/**
 * @name WriteFile
 * @author Team 0x00000001
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class WriteFile 
{	
	protected WriteFile(String pathToFile) throws IOException
	{
		String fileName = pathToFile+"";		
		
		try
		{
			FileWriter fileWrite = new FileWriter(fileName, false);
			BufferedWriter output = new BufferedWriter(fileWrite);
					
			// Write existing table
			for(int row = 0; row < Main.allTasks.getItemCount(); row++)
			{
				for(int col = 0; col < 9; col++)
				{
					output.write(Main.allTasks.getItem(row).getText(col)+"\t");		
				}
				output.write("\r\n");
			}			
			output.close();
		}
				
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());			
		}
	}
}