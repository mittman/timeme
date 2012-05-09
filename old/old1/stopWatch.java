public class stopWatch 
{ 

    private long start;
    static int elapsed;
    static int resume;

    public stopWatch(int resume) 
    {
        start = System.currentTimeMillis() - (resume*1000);
    } 

    public double elapsedTime() 
    {    	
    	long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    } 

    public static void sleep(int seconds)
    {
    	try 
    	{
			Thread.sleep(seconds*1000);
		} 
    	catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
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
    
    public static void increment(int seconds)
    {
    	stopWatch timer = new stopWatch(0);
    	while(elapsed < seconds)
    	{
            elapsed = (int) timer.elapsedTime(); 
            
            int Hour = (elapsed / 3600);
            int Minute = (elapsed % 3600) / 60;
            int Second = (elapsed % 3600) % 60;
            
            String HH = timeFormat(Hour);
            String MM = timeFormat(Minute);
            String SS = timeFormat(Second);
            System.out.println(HH + ":" + MM + ":" + SS); 
            
            //sleep(1);
    	}
    }

    /*
    public static void main(String[] args) 
    { 
        timer = new stopWatch(0); 
        increment(20);
    }
    */
} 
