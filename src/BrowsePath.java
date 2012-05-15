/**
 * @name BrowsePath
 * @project TimeMe
 * @author Team 0x00000001
 */

import java.io.File;
import java.io.IOException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;


public class BrowsePath 
{	
	public static void browseDialog()
	{
		FileDialog browseDialog = new FileDialog(Main.frame, SWT.OPEN);
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
  	    	Main.selectedFile = file;
	      	Main.textDir.setEnabled(true);
	        Main.textDir.setText(Main.selectedFile);
	          	
	        // load table		    	          	
	        try 
	       	{
				new LoadFile(Main.selectedFile);
              	Main.fileStatus.setText("Loaded file");
   	          	Main.configLoaded = true;
   				Main.pauseResume.setEnabled(true);
			} 
          	catch (IOException e) 
          	{
				e.printStackTrace();
              	Main.fileStatus.setText("Invalid file");
   	          	Main.configLoaded = false;
			}
		}
	}
	
	public static void saveDialog()
	{
		FileDialog saveDialog = new FileDialog(Main.frame, SWT.SAVE);
		saveDialog.setFilterNames(new String[] { "TSV Files" });
		saveDialog.setFilterExtensions(new String[] { "*.tsv" });
	  	String file = saveDialog.open();
	  	if(file != null)
	  	{
      		Main.selectedFile = file;
      		Main.textDir.setEnabled(true); 
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