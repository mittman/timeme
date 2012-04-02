/**
 * @name TaskObject
 * @author Team 0x00000001
 */

// vars: start time, end time, time elapsed, subject, notes

public class TaskObject 
{
	
	private String title, notes;
	private long startTime, endTime, timeElapsed;
	private int taskID;
	
   // private long now = System.currentTimeMillis();
    
    
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
