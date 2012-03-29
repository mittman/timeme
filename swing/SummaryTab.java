/**
 * @name ActivityDock
 * @author Team 0x00000001
 */

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class SummaryTab 
{
    protected JPanel SummaryTab()
    {
        JPanel pane = new JPanel();
        
        JComponent component = new JPanel();
        Dimension size = new Dimension(150,100);
        component.setMaximumSize(size);
        component.setPreferredSize(size);
        component.setMinimumSize(size);
        
        String title = "Summary";
 
        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(component);
        return pane;
    }
}
