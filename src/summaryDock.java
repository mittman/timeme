import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class summaryDock 
{

	browsePath selectDirectory;
	
	protected JPanel summaryDock()
    {
        final JPanel pane = new JPanel();
        
        JComponent buttons = new JPanel();
        Dimension dock = new Dimension(200,250);
        buttons.setMaximumSize(dock);
        buttons.setPreferredSize(dock);
        buttons.setMinimumSize(dock);
        
        JButton button1 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>BROWSE</body></html>");
        button1.setAlignmentX(0);
        buttons.add(button1);
        JButton button2 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>SET DEFAULT</body></html>");
        button2.setAlignmentX(0);
        buttons.add(button2);
        JButton button3 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>GENERATE</body></html>");
        button3.setAlignmentX(0);
        buttons.add(button3);
                
        button1.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		System.out.println("summaryDock::BROWSE PRESSED!");
        		
        		File oldDir = null;
        		
        		try
        		{
        			oldDir = selectDirectory.getSelected();
        		}
        		catch (Exception e1)
        		{
        			System.err.println("Error: " + e1.getMessage());
        		}

        		try
        		{
        			selectDirectory = new browsePath(pane,oldDir);
				} 
        		catch (IOException e2) 
        		{
        			System.err.println("Error: " + e2.getMessage());			
        		}
        	}
        });
        
        button2.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		System.out.println("summaryDock::SET DEFAULT PRESSED!");
        		
        		File curDir = null;
        		File saveDir = null;
        		
          		try
        		{
        			curDir = selectDirectory.getCurrent();
        		}
        		catch (Exception e1)
        		{
        			System.err.println("Error: " + e1.getMessage());
        		}
        		
        		try
        		{
        			saveDir = selectDirectory.getSelected();
        		}
        		catch (Exception e2)
        		{
        			System.err.println("Error: " + e2.getMessage());
        		}

        		try
        		{
        			writeConfig setDefault = new writeConfig(curDir,saveDir);
				} 
        		catch (IOException e3) 
        		{
        			System.err.println("Error: " + e3.getMessage());			
        		}
        	}
        });
        
        button3.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		System.out.println("summaryDock::GENERATE PRESSED!");
        	}
        });
        
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.add(buttons);
        return pane;
    }
}