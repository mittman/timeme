/**
 * @name BrowsePath
 * @author Team 0x00000001
 */

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.DirectoryDialog;


public class BrowsePath 
{
	public void browseDir()
	{
	    Main.browseDir.addSelectionListener(new SelectionAdapter() 
	    {
	    	  DirectoryDialog browseDialog = new DirectoryDialog(Main.frame);
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event)
		      {
		    	  browseDialog.setFilterPath(Main.selectedDir);
		    	  browseDialog.setMessage("Please select a directory and click OK");

		    	  String dir = browseDialog.open();
		    	  if (dir != null) 
		    	  {
		    	    	if (dir.equals(Main.selectedDir))
		    	      	{
		    	      		System.out.println("dupe");
		    	      	}
		    	      	else
		    	      	{
		    	      		Main.selectedDir = dir;
		    	          	Main.textDir.setEnabled(true);
		    	          	Main.textDir.setText(Main.selectedDir);
		    	          	// Check if file exists
		    	          	// if file exists then load config
		    	      	}
		    	  }
		    	  Tools.debug("button:" + "browseDir");
		      }
	    });
	}
	
	public void saveDir()
	{
	    Main.saveDir.addSelectionListener(new SelectionAdapter() 
	    {
	      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
	      {
				Tools.debug("button:" + "saveDir");
	      }
	    });
	}
}
	      

