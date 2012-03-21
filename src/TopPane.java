/**
 * @name TopPane
 * @author Team 0x00000001
 */

import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class TopPane 
{
	protected JPanel TopPane()
    {
		JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.add(new RecentPane().RecentPane());
        pane.add(new ClockPane().ClockPane());
        return pane;
    }
}