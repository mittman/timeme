/**
 * @name TaskObject
 * @project TimeMe
 * @author Team 0x00000001
 */

import org.eclipse.swt.widgets.TableItem;


public class TaskObject 
{	
	private String title, notes;
	private long startTime, endTime, timeElapsed;
	private int taskID;
	    
 	/**********************Do stuff***********************/
	public static void saveTask(TaskObject taskToSave)
	{  		
		int saveToRowIndex = searchTableByID(taskToSave.getTaskID());
		if(saveToRowIndex == -1)
		{
			TaskObject.createTask(); //would like to refactor this method to take a taskobject passed dirrectly.
			Tools.debug("saveCurrentTask: " + "No match. Creating new task.");
		}
		else
		{
			// Add to allTasks
			String newRow = StopWatch.timeFormat(saveToRowIndex);		
			String title = taskToSave.getTitle();
			String elapsed = StopWatch.minFormat(taskToSave.getTimeElapsed());
			String recent = "+";
			String taskID = taskToSave.getTaskID() + "";
			String notes = taskToSave.getNotes();
			String start = taskToSave.getStartTime() + "";
			String end = taskToSave.getEndTime() + "";
			String total = taskToSave.getTimeElapsed() + "";
			
			String[] row = { newRow, title, elapsed, recent, taskID, notes, start, end, total };
			Main.allTasks.getItem(saveToRowIndex).setText(row);
			Main.allTasks.setSelection(saveToRowIndex);
			
			// Add to tableList
			String[] list = { title, elapsed, taskID };
			addRecent(0, list);
			
			Tools.debug("saveCurrentTask: " + "Match found. Saving to row index");
		}
	}
	
	public static void unpackFromCurrentTasktoFields(TaskObject taskToUnpack)
	{
		Main.title.setText(taskToUnpack.getTitle());
		Main.textNotes.setText(taskToUnpack.getNotes());
		StopWatch.setElapsed(taskToUnpack.getTimeElapsed());
	}
	
	public static TaskObject returnTaskFromIndex(int rowIndex)
	{
		TaskObject returnable = new TaskObject();
		//unpack the row
		String selectedTitle = Main.allTasks.getItem(rowIndex).getText(1);
		String selectedNotes = Main.allTasks.getItem(rowIndex).getText(5);
		long elapsed = Long.valueOf(Main.allTasks.getItem(rowIndex).getText(8));
		int taskID = Integer.valueOf(Main.allTasks.getItem(rowIndex).getText(4));
		long startTime = Long.valueOf(Main.allTasks.getItem(rowIndex).getText(6));
		long endTime = Long.valueOf(Main.allTasks.getItem(rowIndex).getText(7));
		
		returnable.setTitle(selectedTitle);
		returnable.setNotes(selectedNotes);
		returnable.setTimeElapsed(elapsed); //StopWatch.setElapsed(elapsed); needs to be done outside when unpacking the task
		returnable.setTaskID(taskID);
		returnable.setStartTime(startTime);
		returnable.setEndTime(endTime);
		
		return returnable;
	}
	
	public static int checkRecent(int selected)
	{  		
  		for (int i = 0; i < Main.tableList.getItemCount(); i++)
  		{
  			if(Main.tableList.getItem(i).getText(2).equals(Main.allTasks.getItem(selected).getText(4)))
  			{
  				return i;
  			}
  		}
  		return -1;
	}
	
	public static int searchRecentbyID(int ID)
	{  		
  		for (int i = 0; i < Main.tableList.getItemCount(); i++)
  		{
  			if(Main.tableList.getItem(i).getText(2).equals(ID + ""))
  			{
  				return i;
  			}
  		}
  		return -1;
	}
	
	public static int checkTable(int selected) // searches for an event from the recent list 
	{
  		for (int i = 0; i < Main.allTasks.getItemCount(); i++)
  		{
  			if(Main.tableList.getItem(selected).getText(2).equals(Main.allTasks.getItem(i).getText(4)))
  			{
  				return i;
  			}
  		}
  		return -1;
	}
	
	public static int searchTableByID(int ID) // searches for an event from the recent list 
	{
  		for (int i = 0; i < Main.allTasks.getItemCount(); i++)
  		{
  			
  			if(Main.allTasks.getItem(i).getText(4).equals(ID + ""))
  			{
  				return i;
  			}
  		}
  		return -1;
	}
	
	public static void addRow(int newID, String[] row)
	{
		new TableItem(Main.allTasks, 0, newID).setText(row);
		Main.allTasks.setSelection(newID);
	}
	
	public static void addRecent(int newID, String[] list)
	{
		// Add to tableList
		new TableItem(Main.tableList, 0, newID).setText(list);
		
		if(Main.tableList.getItemCount() > 4)
		{
		  	Main.allTasks.getItem(checkTable(4)).setText(3, "-");
			Main.tableList.remove(4);
		}		
	}
	
	public static void createTask()
	{		
		++Main.maxTaskID;
				
		int newID = Main.maxTaskID;

// Lucas' code		
//SaveObject.saveCurrentToTable(newID);
		
		int rowID = 0;
		if(newID > 1)
		{
			rowID = Integer.parseInt(Main.allTasks.getItem(newID-1).toString().split("[{}]")[1]) + 1; //what is going on here???
		}
		else
		{
			rowID = newID + 1;
		}
		
		Main.currentTask.setTaskID(newID);
		
		// Add to recent list
		Main.taskList.add(Main.currentTask);
		Main.recentTaskID.add(0,newID);
		
		// Add to table
		String newRow = StopWatch.timeFormat(rowID);		
		String title = Main.currentTask.getTitle();
		String elapsed = StopWatch.minFormat(Main.currentTask.getTimeElapsed());
		String recent = "+";
		String taskID = Main.currentTask.getTaskID() + "";
		String notes = Main.currentTask.getNotes();
		String start = Main.currentTask.getStartTime() + "";
		String end = Main.currentTask.getEndTime() + "";
		String total = Main.currentTask.getTimeElapsed() + "";
		
		// Add to allTasks
		String[] row = { newRow, title, elapsed, recent, taskID, notes, start, end, total };
		addRow(newID, row);
		
		// Add to tableList
		String[] list = { title, elapsed, taskID };
		addRecent(0, list);
		
		Main.clockTicking = true;
		StopWatch.newTask();
	}
	
	public static void saveTaskToRow(int ID)
	{
		// Add to table
				String newRow = StopWatch.timeFormat(ID);		
				String title = Main.currentTask.getTitle();
				String elapsed = StopWatch.minFormat(Main.currentTask.getTimeElapsed());
				String recent = "+";
				String taskID = Main.currentTask.getTaskID() + "";
				String notes = Main.currentTask.getNotes();
				String start = Main.currentTask.getStartTime() + "";
				String end = Main.currentTask.getEndTime() + "";
				String total = Main.currentTask.getTimeElapsed() + "";
				
				// Add to allTasks
				String[] row = { newRow, title, elapsed, recent, taskID, notes, start, end, total };
				addRow(ID, row);
				
				// Add to tableList
				String[] list = { title, elapsed, taskID };
				addRecent(0, list);
				
				Main.clockTicking = true;
				StopWatch.newTask();
	}
	
	public static void removeTask(int row)
	{
		try
		{
			Main.inline.dispose();
		}
		catch(Exception unedit){}
		
		row = TableListener.getRow();
	  	int i = checkRecent(row);
	  	if(i != -1)
	  	{
	  		Main.tableList.remove(i);
	  	}
		Main.cell.grabHorizontal = false;
		Main.allTasks.deselectAll();
  		Main.allTasks.remove(row);
  		--Main.maxTaskID;
	}
    
 	/**********************Generic getters and setters***********************/
    	
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
}