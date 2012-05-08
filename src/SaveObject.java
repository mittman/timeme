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
}