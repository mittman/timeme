/**
 * @name TableListener
 * @author Team 0x00000001
 */

import java.text.Collator;
import java.util.Locale;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.TableItem;

public class TableListener 
{
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
	
	public void col4()
	{
	    Main.col4.addSelectionListener(new SelectionAdapter() 
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
					Tools.debug("sort:" + "col4");
		      }
	    });
	}
}
