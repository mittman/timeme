import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class topPane 
{
	protected JPanel topPane()
    {
		JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.add(new recentPane().recentPane());
        pane.add(new clockPane().clockPane());
        return pane;
    }
}