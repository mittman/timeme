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
	public static boolean clearToggle;
	
	public static void tickTock()
	{
		if(Main.clockTicking)
		{
			//pause
			Main.clockTicking = false;
			Main.pauseResume.setText("Resume");
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
		    	  Main.textReport.setEnabled(true);
		    	  Main.textReport.setText("");
		    	  clearToggle = true;
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
		    	  if(!clearToggle)
		    	  {
		    		  clearToggle = true;
		    	  }
		    	  else
		    	  {
		    		  clearToggle = false;
		    	  }
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
	    		  String title = Main.allTasks.getItem(selected).getText(1);
	    		  String notes = Main.allTasks.getItem(selected).getText(5);
	    		  Main.title.setText(title);
	    		  Main.textNotes.setText(notes);
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
		    		  				Main.cell.getItem().setText(column, cellText.getText());
		    		  				int recent = TaskObject.checkRecent(selected);
		    		  				if(recent != -1)
		    		  				{
		    		  					Main.tableList.getItem(recent).setText(1, cellText.getText());
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
		    	  if(Main.allTasks.getSelectionIndex() != -1)
		    	  {
		    		  int row = TableListener.getRow();
		    		  TaskObject.removeTask(row);
		    		  Tools.debug("button:" + "deleteTask:" + row);
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