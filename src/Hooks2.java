import java.text.Collator;
import java.util.Locale;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;

public class Hooks2 
{
	/**
	 * Listener Hooks
	 */

	// topPane button listeners
	public void plus()
	{
		Main2.plus.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				StopWatch2.loadRecord(1);
				Main2.status = false;
	            Main2.resume.setEnabled(true);
	            Main2.pause.setEnabled(false);
	            
	            debug("button:" + "plus");
			}
		});
	}
	
	public void minus()
	{
		Main2.minus.addSelectionListener(new SelectionAdapter() 
		{
			public void widgetSelected(SelectionEvent e) 
			{
				StopWatch2.loadRecord(0);
				Main2.status = false;
	            Main2.resume.setEnabled(true);
	            Main2.pause.setEnabled(false);
				
				debug("button:" + "minus");
			}
		});
	}
	
	// Print debug output to console
	private void debug(String output)
	{
		System.out.println(output);
	}
	

}
