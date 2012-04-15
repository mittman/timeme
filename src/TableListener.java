/**
 * @name TableListener
 * @project TimeMe
 * @author Team 0x00000001
 */

import java.text.Collator;
import java.util.Locale;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;


public class TableListener 
{
	public void tableList()
	{
		Main.tableList.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e)
			{
//				SaveObject.collectCurrentTask();
//lucas				SaveObject.saveCurrentToTable(Main.currentTask.getTaskID());
				//saveToList();
				int selectedID = Integer.valueOf(Main.tableList.getItem(Main.tableList.getSelectionIndex()).getText(2));
				
				int i = 0;
				while(!Main.allTasks.getItem(i).getText(4).equals(selectedID + ""))
				{
					i++;
				}
				String selectedTitle = Main.allTasks.getItem(i).getText(1);
				String selectedNotes = Main.allTasks.getItem(i).getText(5);
				Main.title.setText(selectedTitle);
				Main.textNotes.setText(selectedNotes);		
			}
		});
	}
	
	public static String getRow()
	{
	  	String[] getRow = Main.allTasks.getSelection()[0].toString().split("[{}]");
		return getRow[1];
	}
	
	// Row listener
	public void row()
	{
	    Main.allTasks.addSelectionListener(new SelectionAdapter() 
	    {
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
					Tools.debug("row:" + getRow());
		      }
	    });
	}
	
    // Column listeners
	public void column0()
	{
	    Main.column0.addSelectionListener(new SelectionAdapter() 
	    {	    	 
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
					int colInt = 0;					
					TableItem[] items = Main.allTasks.getItems();
			        Collator collator = Collator.getInstance(Locale.getDefault());
			        for (int i = 1; i < items.length; i++) 
			        {
			        	String v1 = items[i].getText(colInt);
			        	for (int j = 0; j < i; j++) 
			        	{
			        		String v2 = items[j].getText(colInt);
			        		
			        		if ((collator.compare(v1, v2) < 0 && Main.sort0) || (collator.compare(v1, v2) > 0 && !Main.sort0))  
			        		{
			        			String[] values = { items[i].getText(0),items[i].getText(1),items[i].getText(2),items[i].getText(3) };
			        			items[i].dispose();
			                    TableItem item = new TableItem(Main.allTasks, SWT.NONE, j);
			                    item.setText(values);
			                    items = Main.allTasks.getItems();
			                    break;
			                }
			        	}
			        }
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
					int colInt = 1;					
					TableItem[] items = Main.allTasks.getItems();
			        Collator collator = Collator.getInstance(Locale.getDefault());
			        for (int i = 1; i < items.length; i++) 
			        {
			        	String v1 = items[i].getText(colInt);
			        	for (int j = 0; j < i; j++) 
			        	{
			        		String v2 = items[j].getText(colInt);
			        		
			        		if ((collator.compare(v1, v2) < 0 && Main.sort1) || (collator.compare(v1, v2) > 0 && !Main.sort1))  
			        		{
			        			String[] values = { items[i].getText(0),items[i].getText(1),items[i].getText(2),items[i].getText(3) };
			        			items[i].dispose();
			                    TableItem item = new TableItem(Main.allTasks, SWT.NONE, j);
			                    item.setText(values);
			                    items = Main.allTasks.getItems();
			                    break;
			                }
			        	}
			        }
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
					int colInt = 2;					
					TableItem[] items = Main.allTasks.getItems();
			        Collator collator = Collator.getInstance(Locale.getDefault());
			        for (int i = 1; i < items.length; i++) 
			        {
			        	String v1 = items[i].getText(colInt);
			        	for (int j = 0; j < i; j++) 
			        	{
			        		String v2 = items[j].getText(colInt);
			        		
			        		if ((collator.compare(v1, v2) < 0 && Main.sort2) || (collator.compare(v1, v2) > 0 && !Main.sort2))  
			        		{
			        			String[] values = { items[i].getText(0),items[i].getText(1),items[i].getText(2),items[i].getText(3) };
			        			items[i].dispose();
			                    TableItem item = new TableItem(Main.allTasks, SWT.NONE, j);
			                    item.setText(values);
			                    items = Main.allTasks.getItems();
			                    break;
			                }
			        	}
			        }
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
					int colInt = 3;					
					TableItem[] items = Main.allTasks.getItems();
			        Collator collator = Collator.getInstance(Locale.getDefault());
			        for (int i = 1; i < items.length; i++) 
			        {
			        	String v1 = items[i].getText(colInt);
			        	for (int j = 0; j < i; j++) 
			        	{
			        		String v2 = items[j].getText(colInt);
			        		
			        		if ((collator.compare(v1, v2) < 0 && Main.sort3) || (collator.compare(v1, v2) > 0 && !Main.sort3))  
			        		{
			        			String[] values = { items[i].getText(0),items[i].getText(1),items[i].getText(2),items[i].getText(3) };
			        			items[i].dispose();
			                    TableItem item = new TableItem(Main.allTasks, SWT.NONE, j);
			                    item.setText(values);
			                    items = Main.allTasks.getItems();
			                    break;
			                }
			        	}
			        }
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