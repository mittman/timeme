import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    	
    	
    	// Register a change listener
    	tabbedPane.addChangeListener(new ChangeListener() 
    	{
    	    // This method is called whenever the selected tab changes
    	    public void stateChanged(ChangeEvent evt) 
    	    {
    	        JTabbedPane checkTab = (JTabbedPane)evt.getSource();
    	        int tabNum = checkTab.getSelectedIndex();
    	        
    	        String selectedTab = "";
    	        if (tabNum == 0)
    	        {
    	        	selectedTab = "Add Activity";
    	        }
    	        else if (tabNum == 1)
    	        {
    	        	selectedTab = "Manage Activities";
    	        }
    	        else if (tabNum == 2)
    	        {
    	        	selectedTab = "Summary";
    	        }
    	        
    	        System.out.println(selectedTab);
    	    }
    	});

    	
    	return tabbedPane;
    }
}