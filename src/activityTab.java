import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class activityTab 
{
	protected JPanel activityTab()
    {
        JPanel pane = new JPanel();

        JButton button = new JButton("This is a JButton");
        
        String title;

        title = "Add New";
        button.setAlignmentX(0);

        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(button);
        return pane;
    }
}
