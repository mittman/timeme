import org.eclipse.swt.widgets.TableItem;

/**
 * @name TaskObject
 * @project TimeMe
 * @author Team 0x00000001
 */


public class TaskObject 
{	
	private String title, notes;
	private long startTime, endTime, timeElapsed;
	private int taskID;
	private static int row;
	
   // private long now = System.currentTimeMillis();
    
 	/**********************Do stuff***********************/
	public static void createTask()
	{		
		++Main.maxTaskID;
		
		int newID = Main.maxTaskID;
//		int rowID = 0;
//		if(newID > 1)
//		{
//			rowID = Integer.parseInt(Main.allTasks.getItem(newID-1).toString().split("[{}]")[1]) + 1;
//		}
//		else
//		{
//			rowID = newID + 1;
//		}
		
		SaveObject.saveCurrentToTable(newID);
		
//		Main.currentTask.setTaskID(newID);
//		
//		// Add to recent list
//		Main.taskList.add(Main.currentTask);
//		//Main.currentTask = new TaskObject();
//		Main.recentTaskID.add(0,newID);
//		
//		// Add to table
//		String newRow = StopWatch.timeFormat(rowID);
//		String[] newElapsed = StopWatch.clockFormat(Main.currentTask.getTimeElapsed()).split("[.]");
//		
//		String title = Main.currentTask.getTitle();
//		String elapsed = newElapsed[0];
//		String recent = "x";
//		String taskID = Main.currentTask.getTaskID() + "";
//		String start = Main.currentTask.getStartTime() + "";
//		String end = Main.currentTask.getEndTime() + "";
//		String total = Main.currentTask.getTimeElapsed() + "";
//		String notes = Main.currentTask.getNotes();
//		
//		String[] row = new String[] { newRow, title, elapsed, recent, taskID, notes, start, end, total };
//		new TableItem(Main.allTasks, 0, newID).setText(row);
//		Main.allTasks.setSelection(newID);
//		
//		// Add to tableList
//		new TableItem(Main.tableList, 0, 0).setText(new String[] { title, elapsed, taskID });
//		if(Main.tableList.getItemCount() > 4)
//		{
//	  		for (int i = 0; i < Main.allTasks.getItemCount(); i++)
//	  		{
//	  			if(Main.tableList.getItem(4).getText(2).equals(Main.allTasks.getItem(i).getText(4)))
//	  			{
//	  				Main.allTasks.getItem(i).setText(3, "o");
//	  			}
//	  		}
//			Main.tableList.remove(4);
//		}

		
		Main.clockTicking = true;
		StopWatch.newTask();
	}
	
	public static void removeTask()
	{
	  	row = Integer.parseInt(TableListener.getRow()) - 1;  		
  		//Main.taskList.remove(Main.taskList.size()-row-1);
		//Main.list.remove(Main.list.getItemCount()-row-1);
	  	
  		for (int i = 0; i < Main.tableList.getItemCount(); i++)
  		{
  			if(Main.tableList.getItem(i).getText(2).equals(Main.allTasks.getItem(row).getText(4)))
  			{
  				Main.tableList.remove(i);
  			}
  		}
  		Main.allTasks.deselectAll();
  		Main.allTasks.remove(row);
  		--Main.maxTaskID;
	}
    
 	/**********************Generic getters and setters***********************/
    
	public static int getRow() {
		return row;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(long timeElapsed) {
		this.timeElapsed = timeElapsed;
	}
	
	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	
	/********************************************************************/
	
    
   /* 
    private int getTotal(int taskID)
    {
    	int totalTime = 100;
    	return totalTime;
    }
    
    public void saveElapsed(int taskID, int totalTime)
    {
    	WriteFile recordTime = new WriteFile();
    	recordTime.saveRecord(taskID, totalTime);
    }
    
    public long loadElapsed(int taskID)
    {
    	long begin = getTotal(taskID) + now;
    	return begin;
    }
    */
}
