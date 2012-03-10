import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class bottomPane 
{
	protected JTabbedPane bottomPane()
    {
        JTabbedPane tabbedPane = new JTabbedPane();
         
        JPanel tab1 = new JPanel();
        tab1.add(new activityTab().activityTab());
        tab1.add(new activityDock().activityDock());
    	tabbedPane.addTab("<html><body topmargin=8 marginheight=10 leftmargin=10 marginwidth=18>Add Activity</body></html>", tab1);
        
        JPanel tab2 = new JPanel();
        tab2.add(new manageTab().manageTab());
        tab2.add(new manageDock().manageDock());
    	tabbedPane.addTab("<html><body topmargin=8 marginheight=10 leftmargin=10 marginwidth=18>Manage Activities</body></html>", tab2);
  
        JPanel tab3 = new JPanel();
        tab3.add(new summaryTab().summaryTab());
        tab3.add(new summaryDock().summaryDock());
    	tabbedPane.addTab("<html><body topmargin=8 marginheight=10 leftmargin=10 marginwidth=18>Summary</body></html>", tab3);
    	return tabbedPane;
    }
}