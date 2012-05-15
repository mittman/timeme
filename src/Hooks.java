/**
 * @name Hooks
 * @project TimeMe
 * @author Team 0x00000001
 */

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;


public class Hooks 
{	
	public static void tickTock()
	{
		if(Main.clockTicking)
		{
			//pause
			Main.clockTicking = false;
			Main.pauseResume.setText("Resume");
			TaskObject.saveCurrentToRow();
			Main.frame.setText("TimeMe [paused]");
			Tools.debug("button:" + "pause");			
		}
		else
		{
			//resume
			Main.clockTicking = true;
			StopWatch.resume();
			Main.pauseResume.setText("Pause");
			Main.frame.setText("TimeMe " + StopWatch.minFormat(StopWatch.elapsed));
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
				if(Main.clockTicking)
				{
					String elapsed = Main.allTasks.getItem(TaskObject.checkTable(0)).getText(2);
					Main.recentTasks.getItem(0).setText(1, elapsed);
				}
				else
				{
					String total = Main.allTasks.getItem(TaskObject.checkTable(0)).getText(8);
					StopWatch.setElapsed(Long.parseLong(total));
					Main.recentTasks.getItem(0).setText(1, "Running");
				}
				tickTock();
			}
		});
	}
			
	public void newTask()
	{
		Main.newTask.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{	
				++Main.untitled;
				SaveObject.collectCurrentTask();
				TaskObject.saveTask(Main.currentTask);
				Main.pauseResume.setText("Pause");
				Main.bottomPane.setSelection(Main.tab1);
				Main.title.setText("Untitled-" + Main.untitled);
				Main.textNotes.setText("Notes");
				Main.currentTask.setTaskID(-1);
				
				TaskObject.newTask();

				if(!Main.clockTicking)
				{
					tickTock();
				}
				StopWatch.newTask();
				Main.recentTasks.getItem(0).setText(1, "Running");
											
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
		    	  Main.textReport.setEnabled(true);
		    	  Main.textReport.setText("");
		    	  ReportObject.newReport();
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
		    	  Main.textReport.setText("");
		    	  Main.textReport.setEnabled(false);
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
	    	  if(Main.allTasks.getSelectionIndex() != -1)
	    	  {
	    		  int selected = Main.allTasks.getSelectionIndex();

	    		  SaveObject.collectCurrentTask();
	    		  TaskObject tempTask = TaskObject.returnTaskFromIndex(selected);
	    		  TaskObject.saveTask(Main.currentTask);
	    		  Main.currentTask = tempTask;
	    		  TaskObject.unpackFromCurrentTasktoFields(Main.currentTask);
	    		  Hooks.tickTock();
	    		  StopWatch.setElapsed(Main.currentTask.getTotal());
	    		  Hooks.tickTock();
	    		  
	    		  Main.bottomPane.setSelection(Main.tab1);
	    	  }	    	
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
	    	  final int column = 2;
	    	  final int selected = Main.allTasks.getSelectionIndex();
	    	  if(selected != -1)
	    	  {
		  		  int recent = TaskObject.checkRecent(selected);
		  		  if(Main.clockTicking && recent == 0)
			      {
					Hooks.tickTock();
					SaveObject.collectCurrentTask();
				  }
		  		  
	    		  Main.inline = new Text(Main.allTasks, 0);
	    		  Main.cell.grabHorizontal = true;
	    		  Main.cell.setEditor(Main.inline, Main.allTasks.getItem(selected), column);
	    		  Main.inline.setText(Main.allTasks.getItem(selected).getText(column));
	    		  Main.inline.selectAll();
	    		  Main.inline.setFocus();
	    		  Main.inline.addModifyListener(new ModifyListener() 
	    		  {
		    		  	public void modifyText(ModifyEvent edit) 
		    		  	{	
		    		  		if(Main.allTasks.getSelectionIndex() == selected)
			    		 	{
		    		  			Text cellText = (Text) Main.cell.getEditor();
		    		  			
		    		  			boolean clockFormat = false;
		    		  			if(cellText.getText().matches("\\d{2}:\\d{2}:\\d{2}"))
		    		  			{
			    		  			clockFormat = true;
			    		  			for (int i = 0; i < 3; i++)
			    		  			{
			    		  				int ddigit = -1;
			    		  				if(cellText.getText().split(":", 3)[i].matches("\\d{2}"))
			    		  				{
			    		  					try
			    		  					{
			    		  						ddigit = Integer.parseInt(cellText.getText().split(":", 3)[i]);
			    		  					}
			    		  					catch (Exception e) 
			    		  					{
			    		  						e.printStackTrace();
			    		  					}
			    		  				}
		    		  					if(!(ddigit >= 0 && ddigit < 60))
		    		  					{
		    		  						clockFormat = false;
		    		  					}
			    		  			}
		    		  			}
		    		  			
		    		  			if(clockFormat)
		    		  			{
		    		  				//update the recent list
		    		  				Main.cell.getItem().setText(column, cellText.getText());
		    		  				int recent = TaskObject.checkRecent(selected);
		    		  				if(recent != -1)
		    		  				{
		    		  					Main.recentTasks.getItem(recent).setText(1, cellText.getText());
		    		  				}
		    		  				
		    		  				//update the actual elapsed time
		    		  				int[] timeInts = new int[3];
		    		  				for (int i = 0; i < 3; i++)
		    		  				{
		    		  					timeInts[i] = Integer.parseInt(cellText.getText().split(":", 3)[i]);
		    		  				}
		    		  				
		    		  				long milliseconds = (timeInts[0]*60*60*1000)+(timeInts[1]*60*1000)+(timeInts[2]*1000);
		    		  				Main.allTasks.getItem(selected).setText(8, milliseconds+"");

	    		  					if(recent == 0)
	    		  					{
		    		  					String total = Main.allTasks.getItem(TaskObject.checkTable(0)).getText(8);
		    							StopWatch.setElapsed(Long.parseLong(total));
		    							Main.clock.setText(StopWatch.clockFormat(Long.parseLong(total)));
	    		  					}
		    		  			}
			    		 	}
		    		  	}
	    		  });
	    	  }
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
		    	  int index = Main.allTasks.getSelectionIndex();
		    	  if(TaskObject.checkRecent(index) == 0)
		    	  {
		    		  StopWatch.clearTimer();
		    	  }
		    	  if(index != -1)
		    	  {
		    		  int row = TableListener.getRow();
		    		  TaskObject.removeTask(row);
		    		  Tools.debug("button:" + "deleteTask:" + row);
		    	  }
		    	  if(Main.allTasks.getItemCount() == 0)
		    	  {
		    		  Main.maxTaskID = -1;
		    		  Main.untitled = 1;
		    		  Main.title.setText("Untitled-" + Main.untitled);
		    		  Main.pauseResume.setText("Start");
		    		  StopWatch.clearTimer();
		    		  TaskObject.newTask();
		    	  }
		    	  Main.recentTasks.setSelection(0);
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
	
	public void browseDialog()
	{
	    Main.browseDialog.addSelectionListener(new SelectionAdapter() 
	    {
	    	public void widgetSelected(org.eclipse.swt.events.SelectionEvent event)
	    	{
	    		BrowsePath.browseDialog();
	    		Tools.debug("button:" + "browseDialog");
	    	}
	    });
	}
	
	public void saveDialog()
	{
	    Main.saveDialog.addSelectionListener(new SelectionAdapter() 
	    {
	    	public void widgetSelected(org.eclipse.swt.events.SelectionEvent event)
	    	{
	    		BrowsePath.saveDialog();
	    		Tools.debug("button:" + "saveDialog");
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