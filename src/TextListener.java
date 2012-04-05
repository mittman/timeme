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
//				if (Main.configLoaded)
//				{
//					if(Main.title.getText().equals("Title"))
//					{
//						Main.newTask.setEnabled(false);
//					}
//					else
//					{
//						Main.newTask.setEnabled(true);
//					}
//				}
	    	  	if((!Main.title.getText().equals("Title")) /*&& Main.configLoaded*/)
	    	  	{
    	  			Main.newTask.setEnabled(true);
	    	  	}
				Tools.debug("input:" + "title");				
			}
	    });
	}
	
	public void textNotes()
	{
	    Main.textNotes.addModifyListener(new ModifyListener()
	    {
			public void modifyText(ModifyEvent e) 
			{
	    	  	//if((!Main.textNotes.getText().equals("Notes")) && Main.configLoaded){}
				Tools.debug("input:" + "textNotes");				
			}
	    });
	}
}
