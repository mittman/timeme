import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;


public class recentPane 
{
    protected JPanel recentPane()
    {
        JPanel pane = new JPanel();

        String[] history = {"recent0", "recent1", "recent2", "recent3", "recent4"};
        JList recent = new JList(history);
        recent.setFont(new Font(recent.getFont().getName(),recent.getFont().getStyle(),20));
        pane.add(recent);

        return pane;
    }
}
