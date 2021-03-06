/**
 * @name TextListener
 * @project TimeMe
 * @author Team 0x00000001
 */

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;


public class TextListener 
{
	public void title()
	{
	    Main.title.addModifyListener(new ModifyListener()
	    {
			public void modifyText(ModifyEvent e) 
			{
	    	  	if(Main.title.isFocusControl())
	    	  	{
	    	  		SaveObject.collectCurrentTask();
	    	  		TaskObject.saveTask(Main.currentTask);
	    	  	}
			}
	    });
	}
	
	public void textNotes()
	{
	    Main.textNotes.addModifyListener(new ModifyListener()
	    {
			public void modifyText(ModifyEvent e) 
			{
	    	  	if(Main.textNotes.isFocusControl())
	    	  	{
	    	  		SaveObject.collectCurrentTask();
	    	  		TaskObject.saveTask(Main.currentTask);
	    	  	}								
			}
	    });
	}
}
