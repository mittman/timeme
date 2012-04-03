/**
 * @name TextListener
 * @project TimeMe
 * @author Team 0x00000001
 */

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;


public class TextListener 
{
	public void title()
	{
	    Main.title.addVerifyListener(new VerifyListener()
	    {
			public void verifyText(VerifyEvent e) 
			{
	    	  	if(Main.title.getText() != "Title" && Main.configLoaded)
	    	  	{
	    	  		Main.newTask.setEnabled(true);
	    	  	}
				Tools.debug("sort:" + "title");				
			}
	    });
	}
	
	public void textNotes()
	{
	    Main.textNotes.addVerifyListener(new VerifyListener()
	    {
			public void verifyText(VerifyEvent e) 
			{

				Tools.debug("sort:" + "textNotes");				
			}
	    });
	}
}
