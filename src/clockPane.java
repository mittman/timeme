import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class clockPane 
{
	static JLabel timer = new JLabel("00:00:00.00");

    protected JPanel clockPane()
    {
        JPanel head = new JPanel();
        timer.setFont(new Font(timer.getFont().getName(),timer.getFont().getStyle(),50));
        timer.setAlignmentX(0);
        head.setLayout(new BoxLayout(head, BoxLayout.Y_AXIS));
        
        JPanel dock = new JPanel();
        JButton button1 = new JButton("<html><body topmargin=8 marginheight=10>RESUME</body></html>");
        button1.setAlignmentX(0);
        JButton button2 = new JButton("<html><body topmargin=8 marginheight=10>PAUSE</body></html>");
        button2.setAlignmentX(0); 
        dock.setLayout(new BoxLayout(dock, BoxLayout.X_AXIS));
        
        button1.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		System.out.println("clockPane::RESUME PRESSED!");
        		
        		//Timer.startTimer(true);
        		for (int i = 0; i < 10; i++)
        		{
        			timer.setText(Timer.getElapsed(1332210787341L));
        		}
        		
        		/*
                stopWatch timer = new stopWatch(0); 
                stopWatch.increment(20);
                */
        	}
        });
        
        button2.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		System.out.println("clockPane::PAUSE PRESSED!");
        		Timer.startTimer(false);

        	}
        });
        
        head.add(timer);
        dock.add(button1);
        dock.add(button2);
        head.add(dock);
        
 
        return head;
    }
}
