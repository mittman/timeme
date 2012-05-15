/**
 * @name TableListener
 * @project TimeMe
 * @author Team 0x00000001
 */

import java.text.Collator;
import java.util.Locale;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;


public class TableListener 
{
	public void recentTasks()
	{
		Main.recentTasks.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e)
			{
				int selected = Main.recentTasks.getSelectionIndex();
				int index = TaskObject.checkTable(selected);
				
				if(selected != 0)
				{
					SaveObject.collectCurrentTask();
					if(index > -1)
					{
						TaskObject tempTask = TaskObject.returnTaskFromIndex(index);				
						TaskObject.saveTask(Main.currentTask);
						Main.currentTask = tempTask;
						TaskObject.saveTask(Main.currentTask);
						TaskObject.unpackFromCurrentTasktoFields(Main.currentTask);
						Hooks.tickTock();
						StopWatch.setElapsed(Main.currentTask.getTotal());
						Hooks.tickTock();
						Main.recentTasks.getItem(0).setText(1, "Running");
					}	
				}
			}
		});
	}
	
	public static int getRow()
	{
		int getRow = Main.allTasks.getSelectionIndex();
		return getRow;
	}
	
	// Row listener
	public void row()
	{
	    Main.allTasks.addSelectionListener(new SelectionAdapter() 
	    {
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
  		  			Main.cell.grabHorizontal = false;
  		  			try
  		  			{
  		  				Main.inline.dispose();
  		  			}
  		  			catch(Exception unedit){}
					Tools.debug("row:" + getRow());
		      }
	    });
	}
	
    // Column listeners
	public static void sort(boolean order, int colInt)
	{
		TableItem[] items = Main.allTasks.getItems();
        Collator collator = Collator.getInstance(Locale.getDefault());
        for (int i = 1; i < items.length; i++) 
        {
        	String v1 = items[i].getText(colInt);
        	for (int j = 0; j < i; j++) 
        	{
        		String v2 = items[j].getText(colInt);
        		
        		if ((collator.compare(v1, v2) < 0 && order) || (collator.compare(v1, v2) > 0 && !order))  
        		{
        			String[] values = { items[i].getText(0),items[i].getText(1),
        					items[i].getText(2),items[i].getText(3),items[i].getText(4),
        					items[i].getText(5),items[i].getText(6),items[i].getText(7),
        					items[i].getText(8) };
        			items[i].dispose();
                    TableItem item = new TableItem(Main.allTasks, 0, j);
                    item.setText(values);
                    items = Main.allTasks.getItems();
                    break;
                }
        	}
        }
	}
	
	public void column0()
	{
	    Main.column0.addSelectionListener(new SelectionAdapter() 
	    {	    	 
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
		    	  sort(Main.sort0, 0);
		          if(!Main.sort0)
		          {
		          	Main.sort0 = true;
		          }
		          else
		          {
		          	Main.sort0 = false;
		          }
		    	  Tools.debug("sort:" + "column0");
		      }
	    });
	}
	
	public void column1()
	{
	    Main.column1.addSelectionListener(new SelectionAdapter() 
	    {	    	 
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
		    	  sort(Main.sort1, 1);
		          if(!Main.sort1)
		          {
		          	Main.sort1 = true;
		          }
		          else
		          {
		          	Main.sort1 = false;
		          }
		    	  Tools.debug("sort:" + "column1");
		      }
	    });
	}
	
	public void column2()
	{
	    Main.column2.addSelectionListener(new SelectionAdapter() 
	    {	    	 
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
		    	  sort(Main.sort2, 2);
		          if(!Main.sort2)
		          {
		          	Main.sort2 = true;
		          }
		          else
		          {
		          	Main.sort2 = false;
		          }
		    	  Tools.debug("sort:" + "column2");
		      }
	    });
	}
	
	public void column3()
	{
	    Main.column3.addSelectionListener(new SelectionAdapter() 
	    {	    	 
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
		    	  sort(Main.sort3, 3);
		          if(!Main.sort3)
		          {
		          	Main.sort3 = true;
		          }
		          else
		          {
		          	Main.sort3 = false;
		          }
		    	  Tools.debug("sort:" + "column3");
		      }
	    });
	}
}