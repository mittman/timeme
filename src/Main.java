/**
 * @name Main
 * @project TimeMe
 * @author Team 0x00000001
 */

import java.util.LinkedList;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;


public class Main implements SelectionListener
{
	protected static Shell frame;
	
	public static boolean clockTicking;
	public static boolean configLoaded;
	public static boolean interfaceDown;
	public static boolean reportToggle;
	public static boolean taskToggle;
	public static Button browseDir;
	public static Button clearReport;
	public static Button collapse;
	public static Button deleteTask;
	public static Button editNotes;
	public static Button editTime;
	public static Button favoriteTask;
	public static Button genReport;
	public static Button minus;
	public static Button modeEnd;
	public static Button modePersonal;
	public static Button modeStart;
	public static Button modeWork;
	public static Button newTask;
	public static Button pauseResume;
	public static Button plus;
	public static Button saveDir;
	public static Button unloadDir;
	public static Display display;
	public static int maxTaskID;
	public static Label clock;
	public static LinkedList<Integer> recentTaskID;
	public static LinkedList<TaskObject> taskList;
	public static List list;
	public static Rectangle down;
	public static Rectangle up;
	public static String selectedDir;  
	public static StyledText textNotes;
	public static TabFolder bottomPane;	
	public static TabItem tab1; 
	public static TabItem tab2; 
	public static TabItem tab3; 
	public static TabItem tab4;
	public static Table allTasks;
	public static TableColumn col1;
	public static TableColumn col2;
	public static TableColumn col3;
	public static TableColumn col4;
	public static Text textDir;
	public static Text textReport;
	public static Text title;


	/**
	 * Initialize variables.
	 */
	protected void initialize() 
	{
		configLoaded = false;
		taskList = new LinkedList<TaskObject>();
		recentTaskID = new LinkedList<Integer>();
		recentTaskID.add(new Integer(-1));
		maxTaskID = -1;
		reportToggle = false;
		taskToggle = false;
	    selectedDir = "";  	    
		interfaceDown = true;
		up = new Rectangle(0,0,0,0);
		down = new Rectangle(0, 150, 424, 226);
	}

	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Extra Functions
	 */
	
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
	
	public static void load()
	{
		pauseResume.setEnabled(true);
		newTask.setEnabled(true);
		saveDir.setEnabled(true);
		unloadDir.setEnabled(true);
      	textDir.setEnabled(true);
	}
	
	public static void unload()
	{
		pauseResume.setEnabled(false);
		newTask.setEnabled(false);
		saveDir.setEnabled(false);
		unloadDir.setEnabled(false);
      	textDir.setEnabled(false);
	}
	
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
		initialize();
				
		Composite topPane = new Composite(frame, SWT.NONE);
		topPane.setBounds(5, 5, 424, 135);
		
		collapse = new Button(topPane, SWT.NONE);
		collapse.setBounds(4, 93, 40, 35);
		collapse.setText("<<");
		
  		
		list = new List(topPane, SWT.BORDER | SWT.V_SCROLL);
		list.setBounds(0, 0, 177, 87);
		list.setItems(new String[] {});
		list.setSelection(0);
		
		clock = new Label(topPane, SWT.NONE);
		clock.setFont(SWTResourceManager.getFont("Sans", 27, SWT.BOLD));
		clock.setAlignment(SWT.CENTER);
		clock.setBounds(182, 25, 229, 41);
		clock.setText("00:00:00");
				
		plus = new Button(topPane, SWT.NONE);
		plus.addSelectionListener(this);
		plus.setBounds(136, 97, 25, 25);
		plus.setText("+");
		
		minus = new Button(topPane, SWT.NONE);
		minus.addSelectionListener(this);
		minus.setBounds(105, 97, 25, 25);
		minus.setText("–");
		
		newTask = new Button(topPane, SWT.NONE);
		newTask.addSelectionListener(this);
		newTask.setBounds(182, 77, 112, 50);
		newTask.setText("New Task");
		
		Label vDivider = new Label(topPane, SWT.SEPARATOR | SWT.VERTICAL);
		vDivider.setBounds(177, 0, 4, 135);
		
		pauseResume = new Button(topPane, SWT.NONE);		
		pauseResume.setBounds(299, 77, 112, 50);
		pauseResume.setText("Resume");
					
		bottomPane = new TabFolder(frame, SWT.BORDER);
		bottomPane.setBounds(5, 156, 415, 216);
		
		
		//Notes Tab -------------------------------------------------------
		tab1 = new TabItem(bottomPane, SWT.NONE);
		tab1.setText("Description");	
		final Composite contentsTab1 = new Composite(bottomPane, SWT.NONE);
		tab1.setControl(contentsTab1);
	
		title = new Text(contentsTab1, SWT.BORDER);
		title.setText("Title");
		title.setBounds(5, 5, 393, 21);
		
		textNotes = new StyledText(contentsTab1, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		textNotes.setLocation(5, 32);
		textNotes.setSize(393, 147);
		textNotes.setTopMargin(5);
		textNotes.setLeftMargin(5);
		textNotes.setText("Notes");
		textNotes.addListener(SWT.KeyUp, ctrlAListener);
		
		//Task Manage Tab -------------------------------------------
		tab2 = new TabItem(bottomPane, SWT.NONE);
		tab2.setText("Manage Tasks");	
		Composite contentsTab2 = new Composite(bottomPane, SWT.NONE);
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
				
		ScrolledComposite scrolledComposite = new ScrolledComposite(contentsTab2, SWT.BORDER | SWT.V_SCROLL);
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
		
		tab3 = new TabItem(bottomPane, SWT.NONE);
		tab3.setText("Reports");	
		Composite contentsTab3 = new Composite(bottomPane, SWT.NONE);
		tab3.setControl(contentsTab3);
		
		genReport = new Button(contentsTab3, SWT.NONE);
		genReport.setBounds(291, 73, 108, 50);
		genReport.setText("Generate");
		
		clearReport = new Button(contentsTab3, SWT.NONE);
		clearReport.setBounds(291, 129, 108, 50);
		clearReport.setText("Clear");
		
		textReport = new Text(contentsTab3, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		textReport.setLocation(5, 5);
		textReport.setSize(280, 175);
		textReport.setText("\nReport");
		
		Label sortReport = new Label(contentsTab3, SWT.NONE);
		sortReport.setBounds(296, 5, 55, 15);
		sortReport.setText("Sort:");
		
		modeStart = new Button(contentsTab3, SWT.RADIO);
		modeStart.setBounds(296, 28, 90, 16);
		modeStart.setSelection(true);
		modeStart.setText("Start Time");
		
		modeEnd = new Button(contentsTab3, SWT.RADIO);
		modeEnd.setText("End Time");
		modeEnd.setBounds(296, 51, 90, 16);
		
		//Configuration Tab ----------------------------------------
		
		tab4 = new TabItem(bottomPane, SWT.NONE);
		tab4.setText("Configuration");
		Composite contentsTab4 = new Composite(bottomPane, SWT.NONE);
		contentsTab4.setEnabled(false);
		tab4.setControl(contentsTab4);
		
		browseDir = new Button(contentsTab4, SWT.NONE);
		browseDir.setBounds(270, 26, 108, 40);
		browseDir.setText("Browse...");
		
		saveDir = new Button(contentsTab4, SWT.NONE);
		saveDir.setBounds(270, 79, 108, 40);
		saveDir.setText("Save default");
		
		unloadDir = new Button(contentsTab4, SWT.NONE);
		unloadDir.setBounds(270, 131, 108, 40);
		unloadDir.setText("Unload");
				
		Group group1 = new Group(contentsTab4, SWT.NONE);
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
				
		Label hDivider = new Label(frame, SWT.SEPARATOR | SWT.HORIZONTAL);
		hDivider.setBounds(5, 140, 415, 8);

		// Set default tab
		bottomPane.setSelection(tab4);
		unload();

		
		
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////

		
		/**
		 * Listener Hooks
		 */

		Hooks listeners = new Hooks();
		listeners.plus();
		listeners.minus();
		listeners.pauseResume();
		listeners.newTask();
		listeners.collapse();
		listeners.tabListen();
		listeners.modeStart();
		listeners.modeEnd();
		listeners.genReport();
		listeners.clearReport();
		listeners.favoriteTask();
		listeners.editNotes();
		listeners.editTime();
		listeners.deleteTask();
		listeners.modePersonal();
		listeners.modeWork();
		
		TextListener textHooks = new TextListener();
		textHooks.title();
		textHooks.textNotes();
		
		TableListener tableHooks = new TableListener();
		tableHooks.col1();
		tableHooks.col2();
		tableHooks.col3();
		tableHooks.col4();

		BrowsePath path = new BrowsePath();
		path.browseDir();
		path.saveDir();
		path.unloadDir();
		
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