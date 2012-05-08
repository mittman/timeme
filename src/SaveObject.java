import java.awt.Event;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;

/**
 * @name SaveObject
 * @project TimeMe
 * @author Team 0x00000001
 */

public class SaveObject 
{	
	public static void collectCurrentTask() 
	{		
		String taskName = "";
		if(Main.title.getText().equals("Title"))
		{
			taskName = "Untitled-" + Main.untitled;
			++Main.untitled;
			Main.title.setText(taskName);
		}
		else
		{
			taskName = Main.title.getText();
		}
		Main.currentTask.setTitle(Main.title.getText());
		Main.currentTask.setNotes(Main.textNotes.getText());
		Main.currentTask.setTimeElapsed(StopWatch.getElapsed());
		Main.currentTask.setStartTime(StopWatch.begin);
		Main.currentTask.setEndTime(System.currentTimeMillis());
	}
	
	public static void shellListener()
	{
		Main.frame.addListener(SWT.Close, new Listener()
		{
			public void handleEvent(org.eclipse.swt.widgets.Event event)
			{
				if(Main.clockTicking)
				{
					Hooks.tickTock();
				}
				MessageBox closeDialog = new MessageBox(Main.frame, SWT.ICON_QUESTION | SWT.YES | SWT.NO | SWT.CANCEL);
				closeDialog.setMessage("Save changes before closing?");
				int closeChoice = closeDialog.open();
				if(closeChoice == 128)
				{
					Tools.debug("Closed without saving");
				}
				else
				if(closeChoice == 64)
				{
					if(Main.configLoaded)
					{
						BrowsePath.autoSave();
						Tools.debug("Auto-saved");
					}
					else
					{
						BrowsePath.saveDialog();
						Tools.debug("Manually-saved");
					}
				}
				else
				{
					event.doit = false;
					Tools.debug("Cancel closed");
				}
			}
		});
	}
}