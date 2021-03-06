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
		int index = TaskObject.checkTable(0);
		if(index != -1)
		{
			Main.currentTask.setTitle(Main.title.getText());
			Main.currentTask.setElapsed(StopWatch.minFormat(StopWatch.getElapsed()));
			Main.currentTask.setNotes(Main.textNotes.getText());
			Main.currentTask.setStartTime(Long.parseLong(Main.allTasks.getItem(index).getText(6)));
			Main.currentTask.setEndTime(System.currentTimeMillis());
			Main.currentTask.setTotal(StopWatch.getElapsed());
		}
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