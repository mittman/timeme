/**
 * @name WriteFile
 * @author Team 0x00000001
 */

public class WriteFile 
{
	private static long begin;
	
	public WriteFile(int taskID, long elapsed) 
	{
		// TODO Auto-generated constructor stub
	}

	public static void saveRecord(int taskID, long totalTime)
	{
		begin = System.currentTimeMillis() - totalTime;
		System.out.println(getElapsed());
	}
	
	public static String timeFormat(int field)
    {
        if (field < 10)
        {
        	return "0" + field;
        }
        else
        {
        	return "" + field;
        }
    }
	
    public static String getElapsed()
    {
		long elapsed = (System.currentTimeMillis() - begin);
        String HH = timeFormat((int) ((elapsed / 1000) / 3600));
        String MM = timeFormat((int) ((elapsed / 1000) % 3600) / 60);
        String SS = timeFormat((int) ((elapsed / 1000) % 60));
        String MS =  ""+((int) ((elapsed % 1000) / 100));
        return (HH + ":" + MM + ":" + SS + "." + MS);
    }
    
	public static void main(String[] args) 
	{
		saveRecord(10, 250000L);
	}
}
