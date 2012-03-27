/**
 * @name TimeObject
 * @author Team 0x00000001
 */

public class TimeObject 
{
    private long now = System.currentTimeMillis();
    
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
}
