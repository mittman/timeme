/**
 * @name TaskObject
 * @project TimeMe
 * @author Team 0x00000001
 */

import org.eclipse.swt.widgets.TableItem;


public class TaskObject 
{	
	private String title, notes, elapsed;
	private long startTime, endTime, total;
	private int taskID;
	    
 	/**********************Do stuff***********************/
	public static void saveTask(TaskObject taskToSave)
	{  		
		boolean newTask = false;
		int saveToRowIndex = searchTableByID(taskToSave.getTaskID());
		if(saveToRowIndex == -1)
		{
			++Main.maxTaskID;
			taskToSave.setTaskID(Main.maxTaskID);
			saveToRowIndex = Main.maxTaskID;
			newTask = true;
		}
		
		// Add to allTasks
		String newRow = StopWatch.timeFormat(saveToRowIndex);		
		String title = taskToSave.getTitle();
		String elapsed = StopWatch.minFormat(taskToSave.getTotal());
		String recent = "+";
		String taskID = taskToSave.getTaskID() + "";
		String notes = taskToSave.getNotes();
		String start = taskToSave.getStartTime() + "";
		String end = taskToSave.getEndTime() + "";
		String total = taskToSave.getTotal() + "";
		String[] row = { newRow, title, elapsed, recent, taskID, notes, start, end, total };
		
		if(newTask)
		{
			new TableItem(Main.allTasks, 0, saveToRowIndex).setText(row);
		}
		else 
		{
			Main.allTasks.getItem(saveToRowIndex).setText(row);
		}
		Main.allTasks.setSelection(saveToRowIndex);
		
		// Add to recentTasks
		String[] list = { title, elapsed, taskID };
		addRecent(0, list);
		Main.recentTasks.setSelection(0);
		
		//Tools.debug("saveCurrentTask: " + "Match found. Saving to row index");
	}
	
	public static void unpackFromCurrentTasktoFields(TaskObject taskToUnpack)
	{
		Main.title.setText(taskToUnpack.getTitle());
		Main.textNotes.setText(taskToUnpack.getNotes());
		Main.clock.setText(taskToUnpack.getElapsed());
		StopWatch.setElapsed(taskToUnpack.getTotal());
	}
	
	public static TaskObject returnTaskFromIndex(int rowIndex)
	{
		TaskObject returnable = new TaskObject();
		//unpack the row
		String selectedTitle = Main.allTasks.getItem(rowIndex).getText(1);
		String selectedElapsed = Main.allTasks.getItem(rowIndex).getText(2);
		int selectedTaskID = Integer.parseInt(Main.allTasks.getItem(rowIndex).getText(4));
		String selectedNotes = Main.allTasks.getItem(rowIndex).getText(5);
		long selectedStartTime = Long.parseLong(Main.allTasks.getItem(rowIndex).getText(6));
		long selectedEndTime = Long.parseLong(Main.allTasks.getItem(rowIndex).getText(7));
		long selectedTotal = Long.parseLong(Main.allTasks.getItem(rowIndex).getText(8));
		
		returnable.setTitle(selectedTitle);
		returnable.setElapsed(selectedElapsed);
		returnable.setTaskID(selectedTaskID);
		returnable.setNotes(selectedNotes);
		returnable.setStartTime(selectedStartTime);
		returnable.setEndTime(selectedEndTime);
		returnable.setTotal(selectedTotal);
		return returnable;
	}
	
	public static int checkRecent(int selected)
	{  		
  		for (int i = 0; i < Main.recentTasks.getItemCount(); i++)
  		{
  			if(Main.recentTasks.getItem(i).getText(2).equals(Main.allTasks.getItem(selected).getText(4)))
  			{
  				return i;
  			}
  		}
  		return -1;
	}
	
	public static int searchRecentbyID(int ID)
	{  		
  		for (int i = 0; i < Main.recentTasks.getItemCount(); i++)
  		{
  			if(Main.recentTasks.getItem(i).getText(2).equals(ID + ""))
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
  			if(Main.recentTasks.getItem(selected).getText(2).equals(Main.allTasks.getItem(i).getText(4)))
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
	}
	
	public static void addRecent(int newID, String[] list)
	{
		if(searchRecentbyID(Integer.valueOf(list[2])) != -1) //remove from list if already present
		{
			Main.recentTasks.remove(searchRecentbyID(Integer.valueOf(list[2])));
		}
		
		new TableItem(Main.recentTasks, 0, newID).setText(list); // Add to top of recentTasks
		
		if(Main.recentTasks.getItemCount() > 4)
		{
		  	Main.allTasks.getItem(checkTable(4)).setText(3, "-");
			Main.recentTasks.remove(4);
		}		
	}
	
	public static void createTask()
	{		
		++Main.maxTaskID;
				
		int newID = Main.maxTaskID;
		int rowID = 0;		
		Main.currentTask.setTaskID(newID);
		
		// Add to table
		String newRow = StopWatch.timeFormat(rowID);		
		String title = Main.currentTask.getTitle();
		String elapsed = StopWatch.minFormat(Main.currentTask.getTotal());
		String recent = "+";
		String taskID = Main.currentTask.getTaskID() + "";
		String notes = Main.currentTask.getNotes();
		String start = Main.currentTask.getStartTime() + "";
		String end = Main.currentTask.getEndTime() + "";
		String total = Main.currentTask.getTotal() + "";
		
		// Add to allTasks
		String[] row = { newRow, title, elapsed, recent, taskID, notes, start, end, total };
		addRow(newID, row);
		
		Main.clockTicking = true;
		StopWatch.newTask();
	}
	
	public static void saveTaskToRow(int ID)
	{
		// Add to table
				String newRow = StopWatch.timeFormat(ID);		
				String title = Main.currentTask.getTitle();
				String elapsed = StopWatch.minFormat(Main.currentTask.getTotal());
				String recent = "+";
				String taskID = Main.currentTask.getTaskID() + "";
				String notes = Main.currentTask.getNotes();
				String start = Main.currentTask.getStartTime() + "";
				String end = Main.currentTask.getEndTime() + "";
				String total = Main.currentTask.getTotal() + "";
				
				// Add to allTasks
				String[] row = { newRow, title, elapsed, recent, taskID, notes, start, end, total };
				addRow(ID, row);
				
				// Add to recentTasks
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
	  		Main.recentTasks.remove(i);
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

	public String getElapsed() {
		return elapsed;
	}

	public void setElapsed(String elapsed) {
		this.elapsed = elapsed;
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

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
}