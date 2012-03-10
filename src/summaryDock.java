import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class summaryDock 
{
	protected JPanel summaryDock()
    {
        JPanel pane = new JPanel();
        
        JComponent buttons = new JPanel();
        Dimension dock = new Dimension(200,250);
        buttons.setMaximumSize(dock);
        buttons.setPreferredSize(dock);
        buttons.setMinimumSize(dock);
        
        JButton button1 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>BROWSE</body></html>");
        button1.setAlignmentX(0);
        JButton button2 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>GENERATE</body></html>");
        button2.setAlignmentX(0);
        buttons.add(button1);
        buttons.add(button2);
                
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.add(buttons);
        return pane;
    }
}
