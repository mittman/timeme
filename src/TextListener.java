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
	    	  	if((!Main.title.getText().equals("Title")) && Main.title.isFocusControl())
	    	  	{
	    	  		Tools.debug("input:" + "title");
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
	    	  	if((!Main.textNotes.getText().equals("Notes")) && Main.textNotes.isFocusControl())
	    	  	{
	    	  		Tools.debug("input:" + "textNotes");
	    	  	}								
			}
	    });
	}
}
