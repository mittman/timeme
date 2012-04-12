/**
 * @name Hooks
 * @project TimeMe
 * @author Team 0x00000001
 */

import java.util.Iterator;
import java.util.ListIterator;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;
//import java.util.LinkedList;


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
	
//	public void list()
//	{
//		Main.list.addSelectionListener(new SelectionAdapter() 
//		{
//			public void widgetSelected(SelectionEvent e) //The current implementation requires unique task names but doesnt check for them...
//			{
//				SaveObject.collectCurrentTask();
//				//saveToList();
//				String selected = Main.list.getItem(Main.list.getSelectionIndex());
//				Main.currentTask = SaveObject.returnByTaskTitle(selected);			
//				SaveObject.unpackFromCurrentTask();
//	
//				Main.currentTask.setTaskID(Main.list.getSelectionIndex());
//				TaskObject element = Main.taskList.peekLast();
//				if(selected != element.getTitle())
//				{
//					Tools.debug("Current: " + element.getTitle() + " " + "List: " + selected);
//					Main.title.setText(selected);
//				}
//					
//				
////				String selected = Main.list.getItem(Main.list.getSelectionIndex());
////				ListIterator<TaskObject> itr = Main.taskList.listIterator();
////				while(itr.hasNext()) 
////				{
////					TaskObject element = (TaskObject) itr.next(); 
////					Tools.debug("selected: " + selected + "element: " + element.getTitle());
////					if(selected == element.getTitle())
////					{
////						//loadTask
////						itr.set(element);
////					}
////					selected = Main.list.getItem(Main.list.getSelectionIndex());
////				} 		
//				
//			
//			}
//		});
//	}
		
	public void newTask()
	{
		Main.newTask.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{			            
				SaveObject.collectCurrentTask();
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
		    	  if(Main.allTasks.getSelectionIndex() != -1)
		    	  {
		    		  TaskObject.removeTask();
		    		  Tools.debug("button:" + "deleteTask:" + (TaskObject.getRow()+1));
		    	  }
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
		
}
