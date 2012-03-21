/**
 * @name ActivityDock
 * @author Team 0x00000001
 */

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ClockPane 
{
	static JLabel timeText = new JLabel("00:00:00.0");
	StopWatch display = new StopWatch();

    protected JPanel ClockPane()
    {
        JPanel head = new JPanel();
        head.setLayout(new BoxLayout(head, BoxLayout.Y_AXIS));
        
        JPanel dock = new JPanel();
        dock.setLayout(new BoxLayout(dock, BoxLayout.X_AXIS));
        dock.setAlignmentX(0);


        timeText.setFont(new Font(timeText.getFont().getName(),timeText.getFont().getStyle(),50));
        
        final JButton button1 = new JButton("Resume");
        button1.setFont(new Font(button1.getFont().getName(),button1.getFont().getStyle(),20));
        button1.setMargin(new Insets(10, 25, 10, 25));
        
        final JButton button2 = new JButton("Pause ");
        button2.setFont(new Font(button2.getFont().getName(),button2.getFont().getStyle(),20));
        button2.setMargin(new Insets(10, 25, 10, 25));
        button2.setEnabled(false);
        
        dock.add(button1);        
        dock.add(Box.createRigidArea(new Dimension(10,0)));
        dock.add(button2);
                
        head.add(timeText);
        head.add(dock);
                   
        button1.addActionListener(new ActionListener() 
        {
        	
            public void actionPerformed(ActionEvent f) 
            {
        			System.out.println("clockPane::RESUME PRESSED!");
                	display.start();
                    button1.setEnabled(false);
                    button2.setEnabled(true);
            }
            
        });
                
        button2.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent f)
        	{
        			System.out.println("clockPane::PAUSE PRESSED!");
                	display.stop();
                    button2.setEnabled(false);
                    button1.setEnabled(true);
        	}
        });
 
        return head;
    }
}
