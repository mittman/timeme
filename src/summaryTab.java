import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class summaryTab 
{
    protected JPanel summaryTab()
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
