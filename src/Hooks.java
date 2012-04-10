/**
 * @name Hooks
 * @project TimeMe
 * @author Team 0x00000001
 */

import java.util.Iterator;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class Hooks 
{
	
	public static void tickTock()
	{
		if(Main.clockTicking)
		{
			//pause
			Main.clockTicking = false;
			Main.pauseResume.setText("Resume");
			Tools.debug("button:" + "pause");
			
		}
		else
		{
			//resume
			Main.clockTicking = true;
			StopWatch.resume();
			Main.pauseResume.setText("Pause");
			Tools.debug("button:" + "resume");
		}
	}
	
	/**
	 * Listener Hooks
	 */

	// topPane button listeners
	
	public void pauseResume()
	{
		Main.pauseResume.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				tickTock();				
			}
		});
	}
	
	public void list()
	{
		Main.list.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) //The current implementation requires unique task names but doesnt check for them...
			{
				gatherToCurrentTask();
				//saveToList();
				String selected = Main.list.getItem(Main.list.getSelectionIndex());
				Iterator itr = Main.taskList.iterator(); 
				while(itr.hasNext()) {
					TaskObject element = (TaskObject) itr.next(); 
					if(selected == element.getTitle())
					{
						//loadTask
					}

				    System.out.print(element + " ");

				} 
				//Main.list.getItem(Main.list.getSelectionIndex())
				Tools.debug("List item: " + selected);
			}
		});
	}
	
	public void newTask()
	{
		Main.newTask.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{			            
				gatherToCurrentTask();
				TaskObject.createTask();
				
				Main.pauseResume.setText("Pause");
				Main.bottomPane.setSelection(Main.tab1);
				Main.title.setText("Title");
				Main.textNotes.setText("Notes");

				Tools.debug("button:" + "new task");
			}

		});
	}
	
	
	// Tab listener
	public void tabListen()
	{
	    Main.bottomPane.addSelectionListener(new SelectionAdapter() 
	    {
	      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
	      {
	    	  Tools.debug("tab:" + (Main.bottomPane.getSelectionIndex() + 1));
	      }
	    });
	}
	
	
	// bottomPane button listeners
	public void genReport()
	{
	    Main.genReport.addSelectionListener(new SelectionAdapter() 
	    {
	      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
	      {
				Tools.debug("button:" + "genReport");
	      }
	    });
	}
	
	public void clearReport()
	{
	    Main.clearReport.addSelectionListener(new SelectionAdapter() 
	    {
	      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
	      {
				Tools.debug("button:" + "clearReport");
	      }
	    });
	}
	
	public void editNotes()
	{
	    Main.editNotes.addSelectionListener(new SelectionAdapter() 
	    {
	      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
	      {
				Tools.debug("button:" + "editNotes");
	      }
	    });
	}
	
	public void editTime()
	{
	    Main.editTime.addSelectionListener(new SelectionAdapter() 
	    {
	      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
	      {
				Tools.debug("button:" + "editTime");
	      }
	    });
	}
    
	public void deleteTask()
	{
	    Main.deleteTask.addSelectionListener(new SelectionAdapter() 
	    {
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
					Tools.debug("button:" + "deleteTask");
		      }
	    });
	}
	
	public void modeStart()
	{
	    Main.modeStart.addSelectionListener(new SelectionAdapter() 
	    {
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
					if(Main.reportToggle)
					{
						Main.reportToggle = false;
						Tools.debug("radio:" + "modeStart");
					}
		      }
	    });
	}
	
	public void modeEnd()
	{
	    Main.modeEnd.addSelectionListener(new SelectionAdapter() 
	    {
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
					if(!Main.reportToggle)
					{
						Main.reportToggle = true;
						Tools.debug("radio:" + "modeEnd");
					}
		      }			
	    });
	}

	
	// Collapse/Expand bottomPane
	public void collapse()
	{
	    Main.collapse.addSelectionListener(new SelectionAdapter() 
	    {
		    public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		    {
				if (Main.interfaceDown == true) 
				{
					Main.bottomPane.setBounds(Main.up);
					Main.collapse.setText(">>");
					Main.interfaceDown = false;
					Tools.debug("panel:" + "collapse");
				} 
				else 
				{
					Main.bottomPane.setBounds(Main.down);
					Main.collapse.setText("<<");
					Main.interfaceDown = true;
					Tools.debug("panel:" + "expand");
				}
				Main.frame.pack ();
		    }
	    });
	}
	
////////////////////////////////////////////////////////
	
	//Utility Functions
	
	private void gatherToCurrentTask() {
		Main.currentTask.setNotes(Main.textNotes.getText());
		
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
		Main.currentTask.setTimeElapsed(StopWatch.getElapsed());
		Main.currentTask.setEndTime(System.currentTimeMillis());
	}

		


}
