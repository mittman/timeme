/**
 * @name BrowsePath
 * @project TimeMe
 * @author Team 0x00000001
 */

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
		    	          	
		    	          	// load table		    	          	
		    	          	try 
		    	          	{
								new LoadFile(Main.selectedFile);
			    	          	Main.fileStatus.setText("Loaded file");
			    	          	Main.configLoaded = true;
							} 
		    	          	catch (IOException e) 
		    	          	{
								e.printStackTrace();
			    	          	Main.fileStatus.setText("Invalid file");
			    	          	Main.configLoaded = false;
							}
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
		    	  saveDialog.setFilterNames(new String[] { "TSV Files" });
		    	  saveDialog.setFilterExtensions(new String[] { "*.tsv" });
		    	  String file = saveDialog.open();

		    	  if(file != null)
		    	  {
	    	      		Main.load();		    	      		
	    	      		Main.selectedFile = file;
	    	          	Main.textDir.setText(Main.selectedFile);
	    	          	
	    	          	// save table	    	          	
	    	          	try 
	    	          	{
							new WriteFile(Main.selectedFile);
		    	          	Main.fileStatus.setText("Saved file");
		    	          	Main.configLoaded = true;
						} 
	    	          	catch (IOException e) 
	    	          	{
							e.printStackTrace();
		    	          	Main.fileStatus.setText("Invalid file");
		    	          	Main.configLoaded = false;
						}
		    	  }
		    	  Tools.debug("button:" + "saveDialog");
		      }
	    });
	}
	
	public static void unload()
	{
		Main.unloadFile.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{	
	          	Main.fileStatus.setText("No file loaded");
	          	Main.configLoaded = false;
			}
		});
	}
	
	public static void autoSave()
	{
      	// save table
		if(Main.configLoaded)
		{
			SaveObject.collectCurrentTask();
			TaskObject.saveTask(Main.currentTask);
          	try 
          	{
				new WriteFile(Main.selectedFile);
	          	Main.fileStatus.setText("Auto-saved");
			} 
          	catch (IOException e2) 
          	{
				e2.printStackTrace();
	          	Main.fileStatus.setText("Save failed");
			}
		}
	}
	
}
	      

