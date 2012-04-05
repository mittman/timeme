/**
 * @name BrowsePath
 * @project TimeMe
 * @author Team 0x00000001
 */

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.FileDialog;


public class BrowsePath 
{
	public void browseDialog()
	{
	    Main.browseDialog.addSelectionListener(new SelectionAdapter() 
	    {
	    	  FileDialog browseDialog = new FileDialog(Main.frame, SWT.OPEN);
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event)
		      {
		    	  //browseDialog.setFilterPath(Main.selectedFile);
		    	  browseDialog.setFilterNames(new String[] { "TSV Files" });
		    	  browseDialog.setFilterExtensions(new String[] { "*.tsv" });
		    	  String file = browseDialog.open();
		    	  
		    	  if(file != null)
		    	  {
		    		  	System.out.println(new File(file).exists());
		    		  
		    	    	if (file.equals(Main.selectedFile))
		    	      	{
		    	    		Tools.debug("browse:" + "dupe");
		    	      	}
		    	      	else
		    	      	{		    	      		
		    	      		Main.load();		    	      		
		    	      		Main.selectedFile = file;
		    	          	Main.textDir.setText(Main.selectedFile);
		    	          	// load config
		    	          	Main.fileStatus.setText("Loaded file");
		    	          	Main.configLoaded = true;
		    	      	}
		    	  }
		    	  Tools.debug("button:" + "browseDialog");
		      }
	    });
	}
	
	public void saveDialog()
	{
	    Main.saveDialog.addSelectionListener(new SelectionAdapter() 
	    {
	    	  FileDialog saveDialog = new FileDialog(Main.frame, SWT.SAVE);
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
		    	  //saveDialog.setFilterPath(Main.selectedFile);
		    	  saveDialog.setFilterNames(new String[] { "TSV Files" });
		    	  saveDialog.setFilterExtensions(new String[] { "*.tsv" });
		    	  String file = saveDialog.open();

		    	  if(file != null)
		    	  {
		    		  	System.out.println(new File(file).exists());		    		  
	    	      		Main.load();		    	      		
	    	      		Main.selectedFile = file;
	    	          	Main.textDir.setText(Main.selectedFile);
	    	          	// load config
	    	          	Main.fileStatus.setText("Save file set");
	    	          	Main.configLoaded = true;
		    	  }
		    	  Tools.debug("button:" + "saveDialog");
		      }
	    });
	}
	
}
	      

