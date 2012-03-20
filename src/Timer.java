
public class Timer 
{

	public static long getTime()
	{
		return System.currentTimeMillis();
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

    public static String getElapsed(long start)
    {
		long elapsed = (getTime() - start);
        String HH = timeFormat((int) (elapsed / 3600000));
        String MM = timeFormat((int) ((elapsed / 1000) % 3600) / 60);
        String SS = timeFormat((int) ((elapsed / 1000) % 3600) % 60);
        String MS = timeFormat((int) ((elapsed) % 3600) % 60);
        //System.out.println(HH + ":" + MM + ":" + SS + "." + MS);
        return (HH + ":" + MM + ":" + SS + "." + MS);
    }
    
    public static void startTimer(boolean state)
    {
    	
    	if (state)
    	{
    		clockPane.timer.setText(getElapsed(1332210787341L));
    	}
    	else
    	{
    		clockPane.timer.setText("00:00:00.00");
    	}

    }
    
    /*
	public static void main(String[] args) 
	{
		for (int i = 0; i < 100; i++)
		{
			getElapsed(1332210787341L);
		}
	}
	*/

}
