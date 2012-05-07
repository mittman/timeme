/**
 * @name ReportObject
 * @project TimeMe
 * @author Team 0x00000001
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;


public class ReportObject 
{
	
	private static String dateFormat(String epoch)
	{
		long date = Long.parseLong(epoch);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(date);
	}
	
	private static void getData(String[][] reports)
	{
	  	  for(int row = 0; row < Main.allTasks.getItemCount(); row++)
	  	  {
		  		String title = Main.allTasks.getItem(row).getText(1);
		  		String elapsed = Main.allTasks.getItem(row).getText(2);
		  		String start = Main.allTasks.getItem(row).getText(6);
		  		String end = Main.allTasks.getItem(row).getText(7);
		  		String notes = Main.allTasks.getItem(row).getText(5);
		  		reports[row] = new String[]{ title, elapsed, start, end, notes };
	  	  }
	}
	
	private static void sortReport(String[][] reports, final int sortMode)
	{
        Arrays.sort(reports, new Comparator<String[]>() 
        {
            @Override
            public int compare(final String[] entry1, final String[] entry2) 
            {
                final String time1 = entry1[sortMode];
                final String time2 = entry2[sortMode];
                if(!Main.reportToggle)
                {
                	return time1.compareTo(time2);
                }
                else
                {
                	return time2.compareTo(time1);
                }
            }
        });
	}
	
	private static void printReport(String[][] reports)
	{
	  	  String output = "";
	  	  String newline = "\r\n\r\n";
	  	  
	  	  for (int array = 0; array < reports.length; array++)
	  	  {
	  		  String title = "Task: " + reports[array][0];
	  		  String elapsed = "\r\n \tElapsed:  " + reports[array][1];
	  		  String start = "\r\n \tStart:      " + dateFormat(reports[array][2]);
	  		  String end = "\r\n \tEnd:        " + dateFormat(reports[array][3]);
	  		  String notes = "\r\n \tNotes:     " + reports[array][4];
	  		  
	  		  if(array == reports.length - 1)
	  		  {
	  			  newline = "";
	  		  }
	  		  
	  		  output = title + elapsed + start + end + notes + newline;
	  		  Main.textReport.setText(Main.textReport.getText() + output);
	  	  }
	}
		
	public static void newReport()
	{
	  	  String[][] reports = new String[Main.allTasks.getItemCount()][4];
	  	  getData(reports);
	  	  
	  	  if(!Main.reportToggle)
	  	  {
	  		  sortReport(reports, 2);
	  	  }
	  	  else
	  	  {
	  		  sortReport(reports, 3);
	  	  }
	  	  
	  	  printReport(reports);	  	 	  	 
	}
}
