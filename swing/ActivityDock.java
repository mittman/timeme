/**
 * @name ActivityDock
 * @author Team 0x00000001
 */

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class ActivityDock 
{
	protected JPanel ActivityDock()
    {
        JPanel pane = new JPanel();
        
        JComponent buttons = new JPanel();
        Dimension dock = new Dimension(200,250);
        buttons.setMaximumSize(dock);
        buttons.setPreferredSize(dock);
        buttons.setMinimumSize(dock);
        
        JButton button1 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>START</body></html>");
        button1.setAlignmentX(0);
        JButton button2 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>STOP</body></html>");
        button2.setAlignmentX(0);
        buttons.add(button1);
        buttons.add(button2);
                
        button1.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		System.out.println("activityDock::START PRESSED!");
        	}
        });
        
        button2.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		System.out.println("activityDock::STOP PRESSED!");
        	}
        });
        
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.add(buttons);
        return pane;
    }
}
