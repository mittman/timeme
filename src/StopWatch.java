
public class StopWatch 
{
	   private static long begin = System.currentTimeMillis();

		public static void resume()
		{
	        new Thread() 
	        {
	            public void run() 
	            {
	                while(Main.status) 
	                {
	                    Main.display.syncExec(new Runnable() 
	                    {
	                        public void run() 
	                        {
	                            Main.clock.setText(getElapsed());
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

	    public static String getElapsed()
	    {
			long elapsed = (System.currentTimeMillis() - begin);
	        String HH = timeFormat((int) ((elapsed / 1000) / 3600));
	        String MM = timeFormat((int) ((elapsed / 1000) % 3600) / 60);
	        String SS = timeFormat((int) ((elapsed / 1000) % 60));
	        String MS =  ""+((int) ((elapsed % 1000) / 100));
	        return (HH + ":" + MM + ":" + SS + "." + MS);
	    }
}
