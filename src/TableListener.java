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
				SaveObject.collectCurrentTask();
				//saveToList();
				int selectedID = Integer.valueOf(Main.tableList.getItem(Main.tableList.getSelectionIndex()).getText(2));
				
				int i = 0;
				while(!Main.allTasks.getItem(i).getText(4).equals(selectedID + ""))
				{
					i++;
				}
				String selectedTitle = Main.allTasks.getItem(i).getText(1);
				String selectedNotes = Main.allTasks.getItem(i).getText(5);
				long elapsedTime = new Long( Main.allTasks.getItem(i).getText(8) );
				Main.title.setText(selectedTitle);
				Main.textNotes.setText(selectedNotes);
				Hooks.tickTock();
				StopWatch.setElapsed(elapsedTime);
				Hooks.tickTock();

				
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
	public void col1()
	{
	    Main.col1.addSelectionListener(new SelectionAdapter() 
	    {
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
					int colInt = 0;
					TableItem[] items = Main.allTasks.getItems();
			        Collator collator = Collator.getInstance(Locale.getDefault());
			        for (int i = 1; i < items.length; i++) 
			        {
			        	String value1 = items[i].getText(colInt);
			        	for (int j = 0; j < i; j++) 
			        	{
			        		String value2 = items[j].getText(colInt);
			        		if (collator.compare(value1, value2) < 0) 
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
					Tools.debug("sort:" + "col1");
		      }
	    });
	}
	
	public void col2()
	{
	    Main.col2.addSelectionListener(new SelectionAdapter() 
	    {
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
					int colInt = 1;
					TableItem[] items = Main.allTasks.getItems();
			        Collator collator = Collator.getInstance(Locale.getDefault());
			        for (int i = 1; i < items.length; i++) 
			        {
			        	String value1 = items[i].getText(colInt);
			        	for (int j = 0; j < i; j++) 
			        	{
			        		String value2 = items[j].getText(colInt);
			        		if (collator.compare(value1, value2) < 0) 
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
					Tools.debug("sort:" + "col2");
		      }
	    });
	}
	
	public void col3()
	{
	    Main.col3.addSelectionListener(new SelectionAdapter() 
	    {
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
					int colInt = 2;
					TableItem[] items = Main.allTasks.getItems();
			        Collator collator = Collator.getInstance(Locale.getDefault());
			        for (int i = 1; i < items.length; i++) 
			        {
			        	String value1 = items[i].getText(colInt);
			        	for (int j = 0; j < i; j++) 
			        	{
			        		String value2 = items[j].getText(colInt);
			        		if (collator.compare(value1, value2) < 0) 
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
					Tools.debug("sort:" + "col3");
		      }
	    });
	}
	
	public void col5()
	{
	    Main.col5.addSelectionListener(new SelectionAdapter() 
	    {
		      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
		      {
					int colInt = 3;
					TableItem[] items = Main.allTasks.getItems();
			        Collator collator = Collator.getInstance(Locale.getDefault());
			        for (int i = 1; i < items.length; i++) 
			        {
			        	String value1 = items[i].getText(colInt);
			        	for (int j = 0; j < i; j++) 
			        	{
			        		String value2 = items[j].getText(colInt);
			        		if (collator.compare(value1, value2) < 0) 
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
					Tools.debug("sort:" + "col5");
		      }
	    });
	}
}
