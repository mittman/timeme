/**
 * @name SaveObject
 * @project TimeMe
 * @author Team 0x00000001
 */

import java.util.Iterator;
import org.eclipse.swt.widgets.TableItem;


public class SaveObject 
{
	public static void saveCurrentToTable(int taskID)
	{
		collectCurrentTask();
		Main.currentTask.setTaskID(taskID);

		/********** is this an entirely new item?*******/ 
		boolean found = false;
		int foundRow = 0;
		for(int i = 0; i<Main.allTasks.getItemCount(); ++i)
		{
			if(Main.allTasks.getItem(i).getText(4).equals(taskID + ""))
				{
					found = true;
					foundRow = i;
				}
		}
		/***********************************************/
		
		/*** setup entirely aesthetic rowID for field 1 in the table.***/
		int rowID = 0;
		if(taskID > 1)
		{
			rowID = Integer.parseInt(Main.allTasks.getItem(taskID-1).toString().split("[{}]")[1]) + 1;
		}
		else
		{
			rowID = taskID + 1;
		}
		/**************************************************************/
		
		// Add to recent list
		//Main.taskList.add(Main.currentTask); //still necessary?
		//Main.currentTask = new TaskObject();
		//Main.recentTaskID.add(0,taskID); //depricated
		
		// Add to table
		
		/***********Gen table String**********/
		String newRow = StopWatch.timeFormat(rowID); //add leading 0 if less than 10
		String[] newElapsed = StopWatch.clockFormat(Main.currentTask.getTimeElapsed()).split("[.]"); //divide off tenth's
		
		String title = Main.currentTask.getTitle();
		String elapsed = newElapsed[0];
		String recent = "R"; //recent by default
		String taskIDString = taskID + "";
		String start = Main.currentTask.getStartTime() + "";
		String end = Main.currentTask.getEndTime() + "";
		String total = Main.currentTask.getTimeElapsed() + "";
		String notes = Main.currentTask.getNotes();
		
		String[] row = new String[] { newRow, title, elapsed, recent, taskIDString, notes, start, end, total };
		/*************************************/
		
		if(!found) new TableItem(Main.allTasks, 0, taskID).setText(row);
		else Main.allTasks.getItem(foundRow).setText(row);
		
		Main.allTasks.setSelection(taskID);
		
		// Add to tableList
		/**If this task was already in the recent list, Deleted it**/		//needs debugging recentRow always =0
		boolean foundRecent = false;
		int recentRow = 0;
		for(int i = 0; i<Main.tableList.getItemCount(); ++i)
		{
			if(Main.tableList.getItem(i).getText(2).equals(taskID + ""))
				{
					foundRecent = true;
					recentRow = i;
				}
		}
		if(foundRecent)	Main.tableList.remove(recentRow);
		
		/***********************************************************/
		new TableItem(Main.tableList, 0, 0).setText(new String[] { title, elapsed, taskIDString });
		if(Main.tableList.getItemCount() > 4)
		{
	  		for (int i = 0; i < Main.allTasks.getItemCount(); i++)
	  		{
	  			if(Main.tableList.getItem(4).getText(2).equals(Main.allTasks.getItem(i).getText(4)))
	  			{
	  				Main.allTasks.getItem(i).setText(3, "");//not recent
	  			}
	  		}
			Main.tableList.remove(4);
		}
	}

		/***********************************************/
		/***********************************************/
		/***********************************************/
		/***********************************************/


	public static void collectCurrentTask() 
	{		
		String taskName = "";
		if(Main.title.getText().equals("Title"))
		{
			taskName = "Untitled-" + Main.untitled;
			++Main.untitled;
			Main.title.setText(taskName);
		}
		else if(Main.title.getText().startsWith("Untitled-", 0))
		{
			taskName = "Untitled-" + Main.untitled;
			++Main.untitled;
			Main.title.setText(taskName);			
		}	
		else
		{
			taskName = Main.title.getText();
		}
		Main.currentTask.setTitle(Main.title.getText());
		Main.currentTask.setNotes(Main.textNotes.getText());
		Main.currentTask.setTimeElapsed(StopWatch.getElapsed());
		Main.currentTask.setEndTime(System.currentTimeMillis());
	}
	
	/////////////////////////////////////////////////////////
	
	public static void unpackFromCurrentTask()
	{
		Main.textNotes.setText(Main.currentTask.getNotes());
		Main.title.setText(Main.currentTask.getTitle());
		StopWatch.setElapsed(Main.currentTask.getTimeElapsed());
	}
	
	public static TaskObject returnByTaskID(int id)
	{
		Iterator<TaskObject> itr = Main.taskList.iterator();
		while(itr.hasNext()) {
			TaskObject element = (TaskObject) itr.next(); 
			if(id == element.getTaskID())
			{
				return element;
			}

		    System.out.print("element " + id + " returned");

		} 
		
		return null; //Element not found
	}
	
	public static TaskObject returnByTaskTitle(String title)
	{
		Iterator<TaskObject> itr = Main.taskList.iterator();
		
		while(itr.hasNext()) {
			TaskObject element = itr.next(); 
			if(title == element.getTitle())
			{
				System.out.print("element " + title + " returned");
				return element;
			}

		} 

		return null;
	}
}