/**
 * @name ReportObject
 * @project TimeMe
 * @author Team 0x00000001
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class ReportObject 
{
	public static String dateFormat(String epoch)
	{
		long date = Long.parseLong(epoch);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(date);
	}
	
	public static void newReport()
	{
	  	  String output = "";
	  	  String newline = "\n\n";

	  	  
	  	  for(int row = 0; row < Main.allTasks.getItemCount(); row++)
	  	  {
	  		  String title = "Task: " + Main.allTasks.getItem(row).getText(1);
	  		  String elapsed = "\n \tElapsed:  " + Main.allTasks.getItem(row).getText(2);
	  		  String start = "\n \tStart:    " + dateFormat(Main.allTasks.getItem(row).getText(6));
	  		  String end = "\n \tEnd:      " + dateFormat(Main.allTasks.getItem(row).getText(7));
	  		  String notes = "\n \tNotes:    " + Main.allTasks.getItem(row).getText(5);
	  		  
	  		  if(row == Main.allTasks.getItemCount() - 1)
	  		  {
	  			  newline = "";
	  		  }
	  		  output = title + elapsed + start + end + notes + newline;
	  		  Main.textReport.setText(Main.textReport.getText() + output);
	  	  }
	}
}
