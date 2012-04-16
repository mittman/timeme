/**
 * @name StopWatch
 * @project TimeMe
 * @author Team 0x00000001
 */

public class StopWatch 
{
	   private static long begin = System.currentTimeMillis();
	   private static long elapsed = 0;
	   
	   public static void newTask()
	   {
		   elapsed = 0;
		   Main.currentTask.setStartTime(begin);
		   resume();
	   }

		public static void resume()
		{
			begin = System.currentTimeMillis() - elapsed;
	        new Thread() 
	        {
	            public void run() 
	            {
	                while(Main.clockTicking) 
	                {
	                    Main.display.syncExec(new Runnable() 
	                    {
	                        public void run() 
	                        {
	                            Main.clock.setText(getFormatedElapsed());
	                        }
	                    });
	                }

	                Main.display.wake();
	            }
	        }

	        .start();
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

		public static void setElapsed(long elapsed)
		{
			StopWatch.elapsed = elapsed;
		}
						
	    public static long getElapsed()
	    {
			return elapsed;
		}

	    public static String getFormatedElapsed()
	    {
	    	elapsed = (System.currentTimeMillis() - begin);
	    	return clockFormat(elapsed);
	    }
	    
		public static String clockFormat(long elapsed)
	    {
	        String HH = timeFormat((int) ((elapsed / 1000) / 3600));
	        String MM = timeFormat((int) ((elapsed / 1000) % 3600) / 60);
	        String SS = timeFormat((int) ((elapsed / 1000) % 60));
	        String MS =  ""+((int) ((elapsed % 1000) / 100));
	        return (HH + ":" + MM + ":" + SS + "." + MS);
	    }		
}
