/**
 * @name BrowsePath
 * @project TimeMe
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
		    	  
		    	  if(dir != null)
		    	  {
		    		  	//System.out.println(new File(dir).exists());
		    		  
		    	    	if (dir.equals(Main.selectedDir))
		    	      	{
		    	      		System.out.println("dupe");
		    	      	}
		    	      	else
		    	      	{		    	      		
		    	      		Main.load();		    	      		
		    	      		Main.selectedDir = dir;
		    	          	Main.textDir.setText(Main.selectedDir + "/timeme.tsv");
		    	          	// load config
		    	          	Main.configLoaded = true;
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
	
	public void unloadDir()
	{
	    Main.unloadDir.addSelectionListener(new SelectionAdapter() 
	    {
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
					Main.clockTicking = false;
  	          		// save config
		    	  	StopWatch.loadRecord(0);
  	          		Main.configLoaded = false;
		          	Main.unload();
		      		Main.selectedDir = null;
		          	Main.textDir.setText("No file loaded");
					Tools.debug("button:" + "unloadDir");
		      }
	    });
	}
	
}
	      

