import java.text.Collator;
import java.util.Locale;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
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


public class Main implements SelectionListener
{
	private Text title;
	private Table allTasks;
	protected Shell frame;
	public static Label clock;
	public static Display display;
	public static boolean status;

	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Extra Functions
	 */

	// Print debug output to console
	private void debug(String output)
	{
		System.out.println(output);
	}
	
	// Select All in text box
	private Listener ctrlAListener = new Listener() 
	{
		   public void handleEvent( Event event ) 
		   {
		      if ( event.stateMask == SWT.CTRL && event.keyCode == 'a' ) 
		      {
		         ((StyledText)event.widget).selectAll();
		      }
		   }
	};	
	
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try 
		{
			Main window = new Main();
			window.open();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Open the window.
	 */
	public void open() 
	{
		display = Display.getDefault();
		createContents();
		frame.open();
		frame.layout();
		while (!frame.isDisposed()) 
		{
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() 
	{
		frame = new Shell();
		frame.setSize(435, 403);
		frame.setText("TimeMe");
		frame.setLayout(null);
				
		Composite topPane = new Composite(frame, SWT.NONE);
		topPane.setBounds(5, 5, 424, 135);
		
		final Button collapse = new Button(topPane, SWT.NONE);
		collapse.setBounds(4, 92, 40, 35);
		collapse.setText("<<");
		
		List list = new List(topPane, SWT.BORDER);
		list.setBounds(0, 0, 130, 87);
		list.setItems(new String[] {"Recent Item 1", "Recent Item 2", "Recent Item 3", "Recent Item 4"});
		list.setSelection(0);
		
		clock = new Label(topPane, SWT.NONE);
		clock.setFont(SWTResourceManager.getFont("Sans", 27, SWT.BOLD));
		clock.setAlignment(SWT.CENTER);
		clock.setBounds(177, 25, 229, 41);
		clock.setText("00:00:00.0");
				
		Button plus = new Button(topPane, SWT.NONE);
		plus.addSelectionListener(this);
		plus.setBounds(136, 10, 25, 25);
		plus.setText("+");
		
		Button minus = new Button(topPane, SWT.NONE);
		minus.addSelectionListener(this);
		minus.setBounds(136, 51, 25, 25);
		minus.setText("—");
		
		final Button resume = new Button(topPane, SWT.NONE);
		resume.setFont(SWTResourceManager.getFont("Sans", 15, SWT.NORMAL));
		resume.setBounds(177, 76, 100, 50);
		resume.setText("Resume");
		
		final Button pause = new Button(topPane, SWT.NONE);
		pause.setFont(SWTResourceManager.getFont("Sans", 15, SWT.NORMAL));
		pause.setEnabled(false);
		pause.setBounds(306, 77, 100, 50);
		pause.setText("Pause");
		
		Button newTask = new Button(topPane, SWT.NONE);
		newTask.addSelectionListener(this);
		newTask.setBounds(60, 93, 70, 35);
		newTask.setText("New Task");
		
		Label vDivider = new Label(topPane, SWT.SEPARATOR | SWT.VERTICAL);
		vDivider.setBounds(167, 0, 4, 125);
		
		final TabFolder bottomPane = new TabFolder(frame, SWT.BORDER);
		bottomPane.setBounds(5, 156, 415, 216);
		
		TabItem tab1 = new TabItem(bottomPane, SWT.NONE);
		tab1.setText("Description");	
		final Composite contentsTab1 = new Composite(bottomPane, SWT.NONE);
		tab1.setControl(contentsTab1);
	
		title = new Text(contentsTab1, SWT.BORDER);
		title.setText("Title");
		title.setBounds(40, 10, 320, 21);
		
		ScrolledComposite scrollNotes = new ScrolledComposite(contentsTab1, SWT.BORDER | SWT.V_SCROLL);
		scrollNotes.setExpandVertical(true);
		scrollNotes.setShowFocusedControl(true);
		scrollNotes.setExpandHorizontal(true);
		scrollNotes.setAlwaysShowScrollBars(true);
		scrollNotes.setBounds(40, 45, 341, 128);
		
		StyledText textNotes = new StyledText(scrollNotes, SWT.BORDER | SWT.WRAP);
		textNotes.setTopMargin(5);
		textNotes.setLeftMargin(5);
		textNotes.setText("Notes");
		scrollNotes.setMinSize(new Point(341, 512));
		textNotes.addListener(SWT.KeyUp, ctrlAListener);
		scrollNotes.setContent(textNotes);
		
		TabItem tab2 = new TabItem(bottomPane, SWT.NONE);
		tab2.setText("Manage Tasks");	
		Composite contentsTab2 = new Composite(bottomPane, SWT.NONE);
		tab2.setControl(contentsTab2);
		
		Button favoriteTask = new Button(contentsTab2, SWT.NONE);
		favoriteTask.setBounds(285, 23, 100, 30);
		favoriteTask.setText("Favorite");
		
		Button editNotes = new Button(contentsTab2, SWT.NONE);
		editNotes.setBounds(285, 63, 100, 30);
		editNotes.setText("Edit Notes");
		
		Button editTime = new Button(contentsTab2, SWT.NONE);
		editTime.addSelectionListener(this);
		editTime.setBounds(285, 103, 100, 30);
		editTime.setText("Edit Time");
		
		Button deleteTask = new Button(contentsTab2, SWT.NONE);
		deleteTask.addSelectionListener(this);
		deleteTask.setBounds(285, 143, 100, 30);
		deleteTask.setText("Delete Task");
				
		ScrolledComposite scrolledComposite = new ScrolledComposite(contentsTab2, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setAlwaysShowScrollBars(true);
		scrolledComposite.setBounds(0, 0, 279, 183);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		allTasks = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		allTasks.setHeaderVisible(true);
		allTasks.setLinesVisible(true);
		
		TableColumn col1 = new TableColumn(allTasks, SWT.NONE);
		col1.setWidth(30);
		col1.setText("##");
		
		TableColumn col2 = new TableColumn(allTasks, SWT.NONE);
		col2.setWidth(130);
		col2.setText("Title");
		
		TableColumn col3 = new TableColumn(allTasks, SWT.NONE);
		col3.setWidth(70);
		col3.setText("Total");
		
		TableColumn col4 = new TableColumn(allTasks, SWT.NONE);
		col4.setWidth(3);
		col4.setText("*");
		
		TableItem row01 = new TableItem(allTasks, SWT.NONE);
		row01.setText(new String[] {"05", "Programming", "03:00:00", "x"});
		
		TableItem row02 = new TableItem(allTasks, SWT.NONE);
		row02.setText(new String[] {"02", "Studying", "02:00:00", "o"});

		TableItem row03 = new TableItem(allTasks, SWT.NONE);
		row03.setText(new String[] {"03", "Reading", "01:00:00", "x"});

		TableItem row04 = new TableItem(allTasks, SWT.NONE);
		row04.setText(new String[] {"04", "Coding", "07:00:00", "x"});

		TableItem row05 = new TableItem(allTasks, SWT.NONE);
		row05.setText(new String[] {"01", "Scripting", "05:00:00", "o"});

		TableItem row06 = new TableItem(allTasks, SWT.NONE);
		row06.setText(new String[] {"06", "Sleeping", "06:00:00", "x"});
		
		TableItem row07 = new TableItem(allTasks, SWT.NONE);
		row07.setText(new String[] {"07", "Eating", "04:00:00", "o"});
		
		TableItem row08 = new TableItem(allTasks, SWT.NONE);
		row08.setText(new String[] {"08", "Pokemon", "08:00:00", "x"});
				
		scrolledComposite.setContent(allTasks);
		scrolledComposite.setMinSize(allTasks.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		TabItem tab3 = new TabItem(bottomPane, SWT.NONE);
		tab3.setText("Reports");	
		Composite contentsTab3 = new Composite(bottomPane, SWT.NONE);
		tab3.setControl(contentsTab3);
		
		Button genReport = new Button(contentsTab3, SWT.NONE);
		genReport.setBounds(291, 30, 108, 50);
		genReport.setText("Generate");
		
		Button clearReport = new Button(contentsTab3, SWT.NONE);
		clearReport.setBounds(291, 105, 108, 50);
		clearReport.setText("Clear");
		
		Scale scale = new Scale(contentsTab3, SWT.NONE);
		scale.setBounds(34, 47, 224, 60);
		
		TabItem tab4 = new TabItem(bottomPane, SWT.NONE);
		tab4.setText("Configuration");
		Composite contentsTab4 = new Composite(bottomPane, SWT.NONE);
		tab4.setControl(contentsTab4);
				
		Label hDivider = new Label(frame, SWT.SEPARATOR | SWT.HORIZONTAL);
		hDivider.setBounds(5, 140, 415, 8);

		// Set default tab
		bottomPane.setSelection(tab4);
		
		
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////

		
		/**
		 * Listener Hooks
		 */

		// button listeners
		plus.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				StopWatch.loadRecord(1);
				status = false;
	            resume.setEnabled(true);
	            pause.setEnabled(false);
	            
	            debug("plus");
			}
		});
		
		minus.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				StopWatch.loadRecord(0);
				status = false;
	            resume.setEnabled(true);
	            pause.setEnabled(false);
				
				debug("minus");
			}
		});
		
		newTask.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				debug("new task");
			}
		});
		
		resume.addSelectionListener(new SelectionAdapter() 
		{			
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				status = true;
				StopWatch.resume();
				resume.setEnabled(false);
	            pause.setEnabled(true);
				debug("resume");
			}
		});
		
		pause.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				status = false;
	            resume.setEnabled(true);
	            pause.setEnabled(false);
				debug("pause");
			}
		});
		
		
		// Tab listener
	    bottomPane.addSelectionListener(new SelectionAdapter() 
	    {
	      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
	      {
	    	  debug("tab" + bottomPane.getSelectionIndex());
	      }
	    });		
		
	    
	    // Column listeners
		col1.addListener(SWT.Selection, new Listener() 
		{
			public void handleEvent(Event e) 
		    {
				int colInt = 0;
				TableItem[] items = allTasks.getItems();
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
		                    TableItem item = new TableItem(allTasks, SWT.NONE, j);
		                    item.setText(values);
		                    items = allTasks.getItems();
		                    break;
		                 }
		        	}
		        }
		    }
		});
		
		col2.addListener(SWT.Selection, new Listener() 
		{
			public void handleEvent(Event e) 
		    {
				int colInt = 1;
				TableItem[] items = allTasks.getItems();
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
		                    TableItem item = new TableItem(allTasks, SWT.NONE, j);
		                    item.setText(values);
		                    items = allTasks.getItems();
		                    break;
		                 }
		        	}
		        }
		    }
		});
		
		col3.addListener(SWT.Selection, new Listener() 
		{
			public void handleEvent(Event e) 
		    {
				int colInt = 2;
				TableItem[] items = allTasks.getItems();
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
		                    TableItem item = new TableItem(allTasks, SWT.NONE, j);
		                    item.setText(values);
		                    items = allTasks.getItems();
		                    break;
		                 }
		        	}
		        }
		    }
		});
		
		col4.addListener(SWT.Selection, new Listener() 
		{
			public void handleEvent(Event e) 
		    {
				int colInt = 3;
				TableItem[] items = allTasks.getItems();
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
		                    TableItem item = new TableItem(allTasks, SWT.NONE, j);
		                    item.setText(values);
		                    items = allTasks.getItems();
		                    break;
		                 }
		        	}
		        }
		    }
		});
	    
		
		// Collapse/Expand bottomPane
		collapse.addSelectionListener(new SelectionAdapter() 
		{
			boolean interfaceDown = true;
			final Rectangle up = new Rectangle(0,0,0,0);
			final Rectangle down = new Rectangle(0, 150, 424, 226);

			public void widgetSelected(SelectionEvent e) 
			{
				if (interfaceDown == true) 
				{
					bottomPane.setBounds(up);
					collapse.setText(">>");
					interfaceDown = false;
					debug("collapse");
				} 
				else 
				{
					bottomPane.setBounds (down);
					collapse.setText("<<");
					interfaceDown = true;
					debug("expand");
				}
				frame.pack ();
			}
		});
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////

	
	/**
	 * Useless Hooks
	 */
		
	public void widgetSelected(SelectionEvent e) {}
	@Override
	public void widgetDefaultSelected(SelectionEvent e) {}
	
}