import java.util.Timer;
import java.util.TimerTask;


public class ReminderBeep 
{
	
  Timer timer;
  static int count = 0;

  public ReminderBeep(int seconds) 
  {
    timer = new Timer();
    timer.schedule(new RemindTask(), seconds * 1000);
  }

  private class RemindTask extends TimerTask 
  {
    public void run() 
    {
      System.out.println("Stop");
      System.exit(0);
    }
  }

  public static int increment()
  {
	  new ReminderBeep(1);	  
	  count++;
	  return count;
  }

  
  public static void main(String args[]) 
  {
	  System.out.println(increment());
	  System.out.println(increment());
	  System.out.println(increment());
	  System.out.println(increment());
	  System.out.println(increment());
	  System.out.println(increment());
	  System.out.println(increment());

  }
}