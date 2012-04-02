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


public class Main2 implements SelectionListener
{
	private Text title;
	private Table allTasks;
    private String selectedDir = "";
    private boolean radioToggle = false;
    
	protected Shell frame;
	
	public static Label clock;
	public static Display display;
	public static boolean status;
	
	public Composite topPane;
	public Button collapse;
	public List list;
	public static Button plus;
	public static Button minus;
	public static Button resume;
	public static Button pause;
	public Button newTask;
	public Label vDivider;
	public TabFolder bottomPane;
	public TabItem tab1;
	public Composite contentsTab1;
	public ScrolledComposite scrollNotes;
	public StyledText textNotes;
	public TabItem tab2;
	public Composite contentsTab2;
	public Button favoriteTask;
	public Button editNotes;
	public Button editTime;
	public Button deleteTask;
	public ScrolledComposite scrolledComposite;
	public TableColumn col1;
	public TableColumn col2;
	public TableColumn col3;
	public TableColumn col4;
	public TableItem row01;
	public TableItem row02;
	public TableItem row03;
	public TableItem row04;
	public TableItem row05;
	public TableItem row06;
	public TableItem row07;
	public TableItem row08;
	public TabItem tab3;
	public Composite contentsTab3;
	public Button genReport;
	public Button clearReport;
	public Scale scale;
	public TabItem tab4;
	public Composite contentsTab4;
	public Button browseDir;
	public Button saveDir;
	public Group group1;
	public Button modePersonal;
	public Button modeWork;
	public Label hDivider;
	

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
	private Text textDir;
	
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
			Main2 window = new Main2();
			window.open();
			
			// Close window gracefully
			System.exit(0);
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
				
		topPane = new Composite(frame, SWT.NONE);
		topPane.setBounds(5, 5, 424, 135);
		
		collapse = new Button(topPane, SWT.NONE);
		collapse.setBounds(4, 93, 40, 35);
		collapse.setText("<<");
		
		list = new List(topPane, SWT.BORDER);
		list.setBounds(0, 0, 130, 87);
		list.setItems(new String[] {"Recent Item 1", "Recent Item 2", "Recent Item 3", "Recent Item 4"});
		list.setSelection(0);
		
		clock = new Label(topPane, SWT.NONE);
		clock.setFont(SWTResourceManager.getFont("Sans", 27, SWT.BOLD));
		clock.setAlignment(SWT.CENTER);
		clock.setBounds(177, 25, 229, 41);
		clock.setText("00:00:00.0");
				
		plus = new Button(topPane, SWT.NONE);
		plus.addSelectionListener(this);
		plus.setBounds(136, 10, 25, 25);
		plus.setText("+");
		
		minus = new Button(topPane, SWT.NONE);
		minus.addSelectionListener(this);
		minus.setBounds(136, 51, 25, 25);
		minus.setText("—");
		
		resume = new Button(topPane, SWT.NONE);
		resume.setFont(SWTResourceManager.getFont("Sans", 15, SWT.NORMAL));
		resume.setBounds(177, 76, 100, 50);
		resume.setText("Resume");
		
		pause = new Button(topPane, SWT.NONE);
		pause.setFont(SWTResourceManager.getFont("Sans", 15, SWT.NORMAL));
		pause.setEnabled(false);
		pause.setBounds(306, 76, 100, 50);
		pause.setText("Pause");
		
		newTask = new Button(topPane, SWT.NONE);
		newTask.addSelectionListener(this);
		newTask.setBounds(50, 93, 80, 35);
		newTask.setText("New Task");
		
		vDivider = new Label(topPane, SWT.SEPARATOR | SWT.VERTICAL);
		vDivider.setBounds(167, 0, 4, 125);
		
		bottomPane = new TabFolder(frame, SWT.BORDER);
		bottomPane.setBounds(5, 156, 415, 216);
		
		tab1 = new TabItem(bottomPane, SWT.NONE);
		tab1.setText("Description");	
		contentsTab1 = new Composite(bottomPane, SWT.NONE);
		tab1.setControl(contentsTab1);
	
		title = new Text(contentsTab1, SWT.BORDER);
		title.setText("Title");
		title.setBounds(40, 10, 320, 21);
		
		scrollNotes = new ScrolledComposite(contentsTab1, SWT.BORDER | SWT.V_SCROLL);
		scrollNotes.setExpandVertical(true);
		scrollNotes.setShowFocusedControl(true);
		scrollNotes.setExpandHorizontal(true);
		scrollNotes.setAlwaysShowScrollBars(true);
		scrollNotes.setBounds(40, 45, 341, 128);
		
		textNotes = new StyledText(scrollNotes, SWT.BORDER | SWT.WRAP);
		textNotes.setTopMargin(5);
		textNotes.setLeftMargin(5);
		textNotes.setText("Notes");
		scrollNotes.setMinSize(new Point(341, 512));
		textNotes.addListener(SWT.KeyUp, ctrlAListener);
		scrollNotes.setContent(textNotes);
		
		tab2 = new TabItem(bottomPane, SWT.NONE);
		tab2.setText("Manage Tasks");	
		contentsTab2 = new Composite(bottomPane, SWT.NONE);
		tab2.setControl(contentsTab2);
		
		favoriteTask = new Button(contentsTab2, SWT.NONE);
		favoriteTask.setBounds(285, 23, 100, 30);
		favoriteTask.setText("Favorite");
		
		editNotes = new Button(contentsTab2, SWT.NONE);
		editNotes.setBounds(285, 63, 100, 30);
		editNotes.setText("Edit Notes");
		
		editTime = new Button(contentsTab2, SWT.NONE);
		editTime.addSelectionListener(this);
		editTime.setBounds(285, 103, 100, 30);
		editTime.setText("Edit Time");
		
		deleteTask = new Button(contentsTab2, SWT.NONE);
		deleteTask.addSelectionListener(this);
		deleteTask.setBounds(285, 143, 100, 30);
		deleteTask.setText("Delete Task");
				
		scrolledComposite = new ScrolledComposite(contentsTab2, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setAlwaysShowScrollBars(true);
		scrolledComposite.setBounds(0, 0, 279, 183);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		allTasks = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		allTasks.setHeaderVisible(true);
		allTasks.setLinesVisible(true);
		
		col1 = new TableColumn(allTasks, SWT.NONE);
		col1.setWidth(30);
		col1.setText("##");
		
		col2 = new TableColumn(allTasks, SWT.NONE);
		col2.setWidth(130);
		col2.setText("Title");
		
		col3 = new TableColumn(allTasks, SWT.NONE);
		col3.setWidth(70);
		col3.setText("Total");
		
		col4 = new TableColumn(allTasks, SWT.NONE);
		col4.setWidth(3);
		col4.setText("*");
		
		row01 = new TableItem(allTasks, SWT.NONE);
		row01.setText(new String[] {"05", "Programming", "03:00:00", "x"});
		
		row02 = new TableItem(allTasks, SWT.NONE);
		row02.setText(new String[] {"02", "Studying", "02:00:00", "o"});

		row03 = new TableItem(allTasks, SWT.NONE);
		row03.setText(new String[] {"03", "Reading", "01:00:00", "x"});

		row04 = new TableItem(allTasks, SWT.NONE);
		row04.setText(new String[] {"04", "Coding", "07:00:00", "x"});

		row05 = new TableItem(allTasks, SWT.NONE);
		row05.setText(new String[] {"01", "Scripting", "05:00:00", "o"});

		row06 = new TableItem(allTasks, SWT.NONE);
		row06.setText(new String[] {"06", "Sleeping", "06:00:00", "x"});
		
		row07 = new TableItem(allTasks, SWT.NONE);
		row07.setText(new String[] {"07", "Eating", "04:00:00", "o"});
		
		row08 = new TableItem(allTasks, SWT.NONE);
		row08.setText(new String[] {"08", "Pokemon", "08:00:00", "x"});
				
		scrolledComposite.setContent(allTasks);
		scrolledComposite.setMinSize(allTasks.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		tab3 = new TabItem(bottomPane, SWT.NONE);
		tab3.setText("Reports");	
		contentsTab3 = new Composite(bottomPane, SWT.NONE);
		tab3.setControl(contentsTab3);
		
		genReport = new Button(contentsTab3, SWT.NONE);
		genReport.setBounds(291, 30, 108, 50);
		genReport.setText("Generate");
		
		clearReport = new Button(contentsTab3, SWT.NONE);
		clearReport.setBounds(291, 105, 108, 50);
		clearReport.setText("Clear");
		
		scale = new Scale(contentsTab3, SWT.NONE);
		scale.setBounds(34, 47, 224, 60);
		
		tab4 = new TabItem(bottomPane, SWT.NONE);
		tab4.setText("Configuration");
		Composite contentsTab4 = new Composite(bottomPane, SWT.NONE);
		contentsTab4.setEnabled(false);
		tab4.setControl(contentsTab4);
		
		browseDir = new Button(contentsTab4, SWT.NONE);
		browseDir.setBounds(270, 26, 108, 40);
		browseDir.setText("Browse...");
		
		saveDir = new Button(contentsTab4, SWT.NONE);
		saveDir.setBounds(270, 102, 108, 40);
		saveDir.setText("Save default");
				
		group1 = new Group(contentsTab4, SWT.NONE);
		group1.setText("Task Mode");
		group1.setBounds(20, 90, 120, 83);
		
		modePersonal = new Button(group1, SWT.RADIO);
		modePersonal.setLocation(10, 21);
		modePersonal.setSize(88, 22);
		modePersonal.setText("Personal");
		
		modeWork = new Button(group1, SWT.RADIO);
		modeWork.setSelection(true);
		modeWork.setLocation(10, 51);
		modeWork.setSize(88, 22);
		modeWork.setText("Work");
		
		textDir = new Text(contentsTab4, SWT.BORDER);
		textDir.setEnabled(false);
		textDir.setEditable(false);
		textDir.setText("No file loaded");
		textDir.setBounds(25, 31, 221, 30);
				
		hDivider = new Label(frame, SWT.SEPARATOR | SWT.HORIZONTAL);
		hDivider.setBounds(5, 140, 415, 8);

		// Set default tab
		bottomPane.setSelection(tab4);
		
		
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////

		Hooks listeners = new Hooks();
		listeners.plus();
		listeners.minus();
		//listeners.newTask();
		//listeners.resume();
		//listeners.pause();
		//listeners.genReport();



		
		newTask.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				StopWatch2.loadRecord(0);
				status = false;
	            resume.setEnabled(true);
	            pause.setEnabled(false);
	    		bottomPane.setSelection(tab1);
				debug("button:" + "new task");
			}
		});
		
		resume.addSelectionListener(new SelectionAdapter() 
		{			
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				status = true;
				StopWatch2.resume();
				resume.setEnabled(false);
	            pause.setEnabled(true);
				debug("button:" + "resume");
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
				debug("button:" + "pause");
			}
		});
		
		
		// Tab listener
	    bottomPane.addSelectionListener(new SelectionAdapter() 
	    {
	      public void widgetSelected(org.eclipse.swt.events.SelectionEvent event) 
	      {
	    	  debug("tab:" + bottomPane.getSelectionIndex());
	      }
	    });
	    
	    
		// bottomPane button listeners
		genReport.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				debug("button:" + "genReport");
			}
		});
		
		clearReport.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				debug("button:" + "clearReport");
			}
		});
	    
		favoriteTask.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				debug("button:" + "favoriteTask");
			}
		});
	    
		editNotes.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				debug("button:" + "editNotes");
			}
		});
	    
		editTime.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				debug("button:" + "editTime");
			}
		});
	    
		deleteTask.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				debug("button:" + "deleteTask");
			}
		});
		
		modePersonal.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				if(!radioToggle)
				{
					radioToggle = true;
					debug("radio:" + "modePersonal");
				}
			}
		});
		
		modeWork.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				if(radioToggle)
				{
					radioToggle = false;
					debug("radio:" + "modeWork");
				}
			}
		});
		
        browseDir.addListener(SWT.Selection, new Listener() 
        {
    	    DirectoryDialog browseDialog = new DirectoryDialog(frame);
            public void handleEvent(Event event) 
            {
                browseDialog.setFilterPath(selectedDir);
                browseDialog.setMessage("Please select a directory and click OK");
                
                String dir = browseDialog.open();
                if (dir != null) 
                {
                	if (dir.equals(selectedDir))
                	{
                		System.out.println("dupe");
                	}
                	else
                	{
                		selectedDir = dir;
                    	textDir.setEnabled(true);
                    	textDir.setText(selectedDir);
                    	// Check if file exists
                    	// if file exists then load config
                	}
                }
				debug("button:" + "browseDir");
            }
        });
		
		saveDir.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				debug("button:" + "saveDir");
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
				debug("sort:" + "col1");
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
				debug("sort:" + "col2");
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
				debug("sort:" + "col3");
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
				debug("sort:" + "col4");
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
					debug("panel:" + "collapse");
				} 
				else 
				{
					bottomPane.setBounds (down);
					collapse.setText("<<");
					interfaceDown = true;
					debug("panel:" + "expand");
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