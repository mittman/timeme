import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class stopWatch implements ActionListener
{
	private Timer eggtimer = new javax.swing.Timer(100, this);
    private long now = System.currentTimeMillis();

    public stopWatch() 
    {
        clockPane.timeText.setText(getElapsed());
    }

    public void actionPerformed(ActionEvent ae) 
    {
        clockPane.timeText.setText(getElapsed());
    }

    public void start() 
    {
        eggtimer.start();
    }

    public void stop() 
    {
        eggtimer.stop();
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

    public String getElapsed()
    {
		long elapsed = (System.currentTimeMillis() - now);
        String HH = timeFormat((int) ((elapsed / 1000) / 3600));
        String MM = timeFormat((int) ((elapsed / 1000) % 3600) / 60);
        String SS = timeFormat((int) ((elapsed / 1000) % 3600) % 60);
        String MS =  ""+((int) ((elapsed % 1000) / 100));
        return (HH + ":" + MM + ":" + SS + "." + MS);
    }
     
}
