
public class StopWatch 
{
	   private static long begin = System.currentTimeMillis();
	   private static long elapsed = 0;
	   
	   public static void newTask()
	   {
		   elapsed = 0;
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
	        String HH = timeFormat((int) ((elapsed / 1000) / 3600));
	        String MM = timeFormat((int) ((elapsed / 1000) % 3600) / 60);
	        String SS = timeFormat((int) ((elapsed / 1000) % 60));
	        String MS =  ""+((int) ((elapsed % 1000) / 100));
	        return (HH + ":" + MM + ":" + SS + "." + MS);
	    }
	    
		public static void saveRecord(int taskID)
		{
			new WriteFile(taskID, elapsed);
		}
		
		public static void loadRecord(int taskID)
		{
			LoadFile foo = new LoadFile(taskID);
			if (taskID == 1)
			{
				begin = System.currentTimeMillis() - foo.getTotal();
			}
			else
			{
				begin = System.currentTimeMillis();
			}
			Main.clock.setText(getFormatedElapsed());
		}
}
