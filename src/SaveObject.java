import java.util.Iterator;

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
		else if(Main.title.getText().startsWith("Untitled-", 0))
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
		Main.currentTask.setEndTime(System.currentTimeMillis());
	}
	
	/////////////////////////////////////////////////////////
	
	public static void unpackFromCurrentTask()
	{
		Main.textNotes.setText(Main.currentTask.getNotes());
		Main.title.setText(Main.currentTask.getTitle());
		StopWatch.setElapsed(Main.currentTask.getTimeElapsed());
	}
	
	public static TaskObject returnByTaskID(int id)
	{
		Iterator<TaskObject> itr = Main.taskList.iterator();
		while(itr.hasNext()) {
			TaskObject element = (TaskObject) itr.next(); 
			if(id == element.getTaskID())
			{
				return element;
			}

		    System.out.print("element " + id + " returned");

		} 
		
		return null; //Element not found
	}
	
	public static TaskObject returnByTaskTitle(String title)
	{
		Iterator<TaskObject> itr = Main.taskList.iterator();
		
		while(itr.hasNext()) {
			TaskObject element = itr.next(); 
			if(title == element.getTitle())
			{
				System.out.print("element " + title + " returned");
				return element;
			}

		} 

		return null;
	}
}
