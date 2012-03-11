import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class manageDock 
{
	protected JPanel manageDock()
    {
        JPanel pane = new JPanel();
        
        JComponent buttons = new JPanel();
        Dimension dock = new Dimension(200,250);
        buttons.setMaximumSize(dock);
        buttons.setPreferredSize(dock);
        buttons.setMinimumSize(dock);
        
        JButton button1 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>RESUME</body></html>");
        button1.setAlignmentX(0);
        JButton button2 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>EDIT</body></html>");
        button2.setAlignmentX(0);
        JButton button3 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>DELETE</body></html>");
        button3.setAlignmentX(0);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
      
        button1.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		System.out.println("manageDock::RESUME PRESSED!");
        	}
        });
        
        button2.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		System.out.println("manageDock::EDIT PRESSED!");
        	}
        });
        
        button3.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		System.out.println("manageDock::DELETE PRESSED!");
        	}
        });

        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.add(buttons);
        return pane;
    }
}
