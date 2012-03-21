/**
 * @name RecentPane
 * @author Team 0x00000001
 */

import java.awt.Font;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class RecentPane 
{
    protected JPanel RecentPane()
    {
        JPanel pane = new JPanel();

        String[] history = {"recent0", "recent1", "recent2", "recent3", "recent4"};
        final JList recent = new JList(history);
        recent.setFont(new Font(recent.getFont().getName(),recent.getFont().getStyle(),20));
        pane.add(recent);
        
        
          recent.addListSelectionListener(new ListSelectionListener()
          {
        	int oldNum = -1;
            public void valueChanged(ListSelectionEvent e)
            {
                int num = recent.getSelectedIndex();
                if (num != oldNum)
                {
                    System.out.println(recent.getSelectedValue());
                }
                oldNum = num;
            }
          });
        
        return pane;
    }
}
