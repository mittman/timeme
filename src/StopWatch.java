/**
 * @name StopWatch
 * @project TimeMe
 * @author Team 0x00000001
 */

public class StopWatch 
{
	   public static long begin = System.currentTimeMillis();
	   private static double change = 0;
	   public static long elapsed = 0;

	   public static void clearTimer()
	   {
			// Stop timer
			if(Main.clockTicking)
			{
				Hooks.tickTock();
			}
			
			// Clear timer
			elapsed = 0;
			Main.currentTask.setStartTime(begin);
			Main.clock.setText("00:00:00.0");
			Main.frame.setText("TimeMe");
	   }
	   
	   public static void newTask()
	   {
		   elapsed = 0;
		   Main.currentTask.setStartTime(begin);
		   change = 0;
		   Main.frame.setText("TimeMe " + minFormat(elapsed));
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
	                            Main.clock.setText(getFormattedElapsed());
	                            countChange();
	                        }
	                    });
	                	try 
                    	{
							Thread.sleep(100);
						} 
                    	catch (InterruptedException e) 
                    	{
                    		e.printStackTrace();
						}
	                }
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
			StopWatch.change = Math.ceil((elapsed/100.0));
		}
						
	    public static long getElapsed()
	    {
			return elapsed;
		}

	    public static String getFormattedElapsed()
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
		
		public static String minFormat(long elapsed)
		{
			return clockFormat(elapsed).split("[.]")[0];
		}
		
		public static void countChange()
		{	
			if((elapsed/100.0) > change)
			{
				Main.frame.setText("TimeMe " + minFormat(elapsed));
				change = Math.ceil((elapsed/100.0));
			}
		}
}