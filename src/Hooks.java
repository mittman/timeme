/**
 * @name Hooks
 * @author Team 0x00000001
 */

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class Hooks 
{
	
	public void tickTock()
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
	public void plus()
	{
		Main.plus.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				StopWatch.loadRecord(1);
				tickTock();					            
	            Tools.debug("button:" + "plus");
			}
		});
	}
	
	public void minus()
	{
		Main.minus.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				StopWatch.loadRecord(0);			
				tickTock();				
				Tools.debug("button:" + "minus");
			}
		});
	}
	
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
			public void widgetSelected(SelectionEvent e) 
			{
			            
				Tools.debug("List item: " + Main.list.getSelectionIndex());
			}
		});
	}
	
	public void newTask()
	{
		Main.newTask.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{			            
				if(Main.maxTaskID != -1) //save task
				{
					//saveTasktoList(recentTaskID.getFirst());
					Main.list.add("prev task", 0);
					//list.add(taskList.get(recentTaskID.getFirst()).getSubject());
				}
				Main.taskList.add(new TaskObject());
				++Main.maxTaskID;
				Main.recentTaskID.add(0,Main.maxTaskID);
				
				Main.clockTicking = true;
				StopWatch.newTask();
				Main.taskList.get(Main.maxTaskID).setStartTime(System.currentTimeMillis());
				Main.pauseResume.setText("Pause");
				Main.bottomPane.setSelection(Main.tab1);
				
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
	
	public void favoriteTask()
	{
	    Main.favoriteTask.addSelectionListener(new SelectionAdapter() 
	    {
	      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
	      {
				Tools.debug("button:" + "favoriteTask");
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
	
	public void modePersonal()
	{
	    Main.modePersonal.addSelectionListener(new SelectionAdapter() 
	    {
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
					if(!Main.taskToggle)
					{
						Main.taskToggle = true;
						Tools.debug("radio:" + "modePersonal");
					}
		      }
	    });
	}
	
	public void modeWork()
	{
	    Main.modeWork.addSelectionListener(new SelectionAdapter() 
	    {
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
					if(Main.taskToggle)
					{
						Main.taskToggle = false;
						Tools.debug("radio:" + "modeWork");
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
		


}
