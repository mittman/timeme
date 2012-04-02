import java.text.Collator;
import java.util.LinkedList;
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


public class Main implements SelectionListener
{
	private Text title;
	private Text txtReport;
	private Table allTasks;
    private String selectedDir = "";
    private boolean radioToggle = false;
    
	protected Shell frame;
	
	public static Label clock;
	public static Display display;
	public static boolean clockTicking;
	
	private Button pauseResume;
	LinkedList<TaskObject> taskList = new LinkedList<TaskObject>();
	int maxTaskID = -1;
	private LinkedList<Integer> recentTaskID = new LinkedList<Integer>();
	List list;
	StyledText textNotes;
	

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
			Main window = new Main();
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
				
		Composite topPane = new Composite(frame, SWT.NONE);
		topPane.setBounds(5, 5, 424, 135);
		
		final Button collapse = new Button(topPane, SWT.NONE);
		collapse.setBounds(4, 93, 40, 35);
		collapse.setText("<<");
		
		recentTaskID.add(new Integer(-1));
		
		list = new List(topPane, SWT.BORDER | SWT.V_SCROLL);
		list.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		list.setBounds(0, 0, 177, 87);
		list.setItems(new String[] {"Recent Item 1", "Recent Item 2", "Recent Item 3", "Recent Item 4", "Recent Item 5", "Recent Item 6", "Recent Item 7"});
		list.setSelection(0);
		
		clock = new Label(topPane, SWT.NONE);
		clock.setFont(SWTResourceManager.getFont("Sans", 27, SWT.BOLD));
		clock.setAlignment(SWT.CENTER);
		clock.setBounds(182, 25, 229, 41);
		clock.setText("00:00:00");
				
		Button plus = new Button(topPane, SWT.NONE);
		plus.addSelectionListener(this);
		plus.setBounds(136, 97, 25, 25);
		plus.setText("+");
		
		Button minus = new Button(topPane, SWT.NONE);
		minus.addSelectionListener(this);
		minus.setBounds(105, 97, 25, 25);
		minus.setText("-");
		
		Button newTask = new Button(topPane, SWT.NONE);
		newTask.addSelectionListener(this);
		newTask.setBounds(182, 77, 112, 50);
		newTask.setText("New Task");
		
		Label vDivider = new Label(topPane, SWT.SEPARATOR | SWT.VERTICAL);
		vDivider.setBounds(177, 0, 4, 135);
		
		pauseResume = new Button(topPane, SWT.NONE);
		
		pauseResume.setBounds(299, 77, 112, 50);
		pauseResume.setText("Pause");
			
		////
		
		final TabFolder bottomPane = new TabFolder(frame, SWT.BORDER);
		bottomPane.setBounds(5, 156, 415, 216);
		
		
		//Notes Tab -------------------------------------------------------
		final TabItem tab1 = new TabItem(bottomPane, SWT.NONE);
		tab1.setText("Description");	
		final Composite contentsTab1 = new Composite(bottomPane, SWT.NONE);
		tab1.setControl(contentsTab1);
	
		title = new Text(contentsTab1, SWT.BORDER);
		title.setText("Title");
		title.setBounds(5, 5, 393, 21);
		
		StyledText textNotes_1 = new StyledText(contentsTab1, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		textNotes_1.setLocation(5, 32);
		textNotes_1.setSize(393, 147);
		textNotes_1.setTopMargin(5);
		textNotes_1.setLeftMargin(5);
		textNotes_1.setText("Notes");
		textNotes_1.addListener(SWT.KeyUp, ctrlAListener);
		
		//Task Manage Tab -------------------------------------------
		final TabItem tab2 = new TabItem(bottomPane, SWT.NONE);
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
		
		//Reports Tab -----------------------------------------------
		
		final TabItem tab3 = new TabItem(bottomPane, SWT.NONE);
		tab3.setText("Reports");	
		Composite contentsTab3 = new Composite(bottomPane, SWT.NONE);
		tab3.setControl(contentsTab3);
		
		Button genReport = new Button(contentsTab3, SWT.NONE);
		genReport.setBounds(291, 73, 108, 50);
		genReport.setText("Generate");
		
		Button clearReport = new Button(contentsTab3, SWT.NONE);
		clearReport.setBounds(291, 129, 108, 50);
		clearReport.setText("Clear");
		
		txtReport = new Text(contentsTab3, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		txtReport.setLocation(5, 5);
		txtReport.setSize(280, 175);
		txtReport.setText("Report");
		
		Button btnReportByStart = new Button(contentsTab3, SWT.RADIO);
		btnReportByStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnReportByStart.setBounds(296, 28, 90, 16);
		btnReportByStart.setText("Start Time");
		
		Label lblReportBy = new Label(contentsTab3, SWT.NONE);
		lblReportBy.setBounds(291, 7, 55, 15);
		lblReportBy.setText("Report By:");
		
		Button btnEndTime = new Button(contentsTab3, SWT.RADIO);
		btnEndTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnEndTime.setText("End Time");
		btnEndTime.setBounds(296, 51, 90, 16);
		
		//Configuration Tab ----------------------------------------
		
		final TabItem tab4 = new TabItem(bottomPane, SWT.NONE);
		tab4.setText("Configuration");
		Composite contentsTab4 = new Composite(bottomPane, SWT.NONE);
		contentsTab4.setEnabled(false);
		tab4.setControl(contentsTab4);
		
		Button browseDir = new Button(contentsTab4, SWT.NONE);
		browseDir.setBounds(270, 26, 108, 40);
		browseDir.setText("Browse...");
		
		Button saveDir = new Button(contentsTab4, SWT.NONE);
		saveDir.setBounds(270, 102, 108, 40);
		saveDir.setText("Save default");
				
		Group group1 = new Group(contentsTab4, SWT.NONE);
		group1.setText("Task Mode");
		group1.setBounds(20, 90, 120, 83);
		
		Button modePersonal = new Button(group1, SWT.RADIO);
		modePersonal.setLocation(10, 21);
		modePersonal.setSize(88, 22);
		modePersonal.setText("Personal");
		
		Button modeWork = new Button(group1, SWT.RADIO);
		modeWork.setSelection(true);
		modeWork.setLocation(10, 51);
		modeWork.setSize(88, 22);
		modeWork.setText("Work");
		
		textDir = new Text(contentsTab4, SWT.BORDER);
		textDir.setEnabled(false);
		textDir.setEditable(false);
		textDir.setText("No file loaded");
		textDir.setBounds(25, 31, 221, 30);
				
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

		// topPane button listeners
		
		list.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				debug("List item: " + list.getSelectionIndex());
			}
		});
		
		plus.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				StopWatch.loadRecord(1);
				clockTicking = false;
				pauseResume.setText("Pause");
				
	            debug("button:" + "plus");
			}
		});
		
		minus.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				StopWatch.loadRecord(0);
				clockTicking = false;
				pauseResume.setText("Pause");
				
				debug("button:" + "minus");
			}
		});
		
		newTask.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				if(maxTaskID != -1) //save task
				{
					//saveTasktoList(recentTaskID.getFirst());
					list.add("privious task to go here", 0);
					//list.add(taskList.get(recentTaskID.getFirst()).getSubject());
				}
				taskList.add(new TaskObject());
				++maxTaskID;
				recentTaskID.add(0,maxTaskID);
				
				clockTicking = true;
				StopWatch.newTask();
				taskList.get(maxTaskID).setStartTime(System.currentTimeMillis());
				pauseResume.setText("Pause");
				
				debug("new task");
			}
		});
		
		pauseResume.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent arg0) {
				//if(!(pauseResume.getText()=="Pause" && clockTicking == false)) //Dont do anything if there's no task loaded (only happens initially)
				//{
			
					if(clockTicking)
					{
						//pause
						clockTicking = false;
						pauseResume.setText("Resume");
						debug("pauseResume:pause");
						
					}
					else
					{
						//resume
						clockTicking = true;
						StopWatch.resume();
						pauseResume.setText("Pause");
			           	debug("pauseResume:resume");
					}
				//}
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
	
	/**
	 * Functions
	 */
	
	void saveTasktoList(int taskID)
	{
		taskList.get(taskID).setTitle(title.getText());
		taskList.get(taskID).setNotes(textNotes.getText());
		taskList.get(taskID).setTimeElapsed(StopWatch.getElapsed());
		taskList.get(taskID).setEndTime(System.currentTimeMillis());
	}
	void refreshRecentTaskUI()
	{
		//needs filling		
	}
}