/**
 * @name Main
 * @project TimeMe
 * @author Team 0x00000001
 */

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
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
	public static boolean sort0;
	public static boolean sort1;
	public static boolean sort2;
	public static boolean sort3;
	public static Button browseDialog;
	public static Button clearReport;
	public static Button collapse;
	public static Button deleteTask;
	public static Button editNotes;
	public static Button editTime;
	public static Button genReport;
	public static Button modeEnd;
	public static Button modeStart;
	public static Button newTask;
	public static Button pauseResume;
	public static Button saveDialog;
	public static Display display;
	public static int maxTaskID;
	public static int untitled;
	public static Label clock;
	public static Label fileStatus;
	public static List list;
	public static Rectangle down;
	public static Rectangle up;
	public static String selectedFile;
	public static StyledText textNotes;
	public static TabFolder bottomPane;	
	public static TabItem tab1; 
	public static TabItem tab2; 
	public static TabItem tab3; 
	public static TabItem tab4;
	public static Table allTasks;
	public static Table recentTasks;
	public static TableColumn column0;
	public static TableColumn column1;
	public static TableColumn column2;
	public static TableColumn column3;
	public static TableColumn column4;
	public static TableColumn column5;
	public static TableColumn column6;
	public static TableColumn column7;
	public static TableColumn column8;
	public static TableColumn recentColumn0;
	public static TableColumn recentColumn1;
	public static TableColumn recentColumn2;
	public static TableEditor cell;
	public static TableItem firstTask;
	public static TaskObject currentTask;
	public static Text inline;
	public static Text textDir;
	public static Text textReport;
	public static Text title;


	/**
	 * Initialize variables.
	 */
	protected void initialize() 
	{
		configLoaded = false;
		untitled = 1;
		currentTask = new TaskObject();
		currentTask.setTitle("Untitled-0");
		currentTask.setElapsed("00:00:00");
		currentTask.setTaskID(0);
		maxTaskID = -1;
		reportToggle = false;
	    selectedFile = "";
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
		   public void handleEvent(Event event) 
		   {
		      if (event.stateMask == SWT.CTRL && event.keyCode == 'a') 
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
		display.setWarnings(false);
		createContents();
		frame.open();
		frame.layout();
		SaveObject.shellListener();

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
		//frame = new Shell(SWT.CLOSE | SWT.TITLE | SWT.MIN);
		frame = new Shell();
		frame.setImage(SWTResourceManager.getImage(Main.class, "timeme-icon.png"));
		frame.setSize(435, 403);
		frame.setText("TimeMe");
		frame.setLayout(null);
		initialize();
						
		//topPane -------------------------------------------------------
		Composite topPane = new Composite(frame, 0);
		topPane.setBounds(5, 5, 424, 135);
		
		collapse = new Button(topPane, 0);
		collapse.setBounds(0, 94, 47, 41);
		collapse.setText("<<");

		recentTasks = new Table(topPane, SWT.BORDER | SWT.FULL_SELECTION);
		recentTasks.setBounds(0, 0, 282, 88);
		recentTasks.setHeaderVisible(false);
		
		// recent title
		recentColumn0 = new TableColumn(recentTasks, 0);
		recentColumn0.setWidth(160);		
		
		// recent elapsed
		recentColumn1 = new TableColumn(recentTasks, 0);
		recentColumn1.setWidth(80);
		
		// recent taskID
		recentColumn2 = new TableColumn(recentTasks, 0);
		recentColumn2.setWidth(0);
		
		clock = new Label(topPane, 0);
		clock.setFont(SWTResourceManager.getFont("Sans", 27, SWT.BOLD));
		clock.setAlignment(SWT.CENTER);
		clock.setBounds(53, 94, 229, 41);
		clock.setText("00:00:00.0");
						
		newTask = new Button(topPane, 0);
		newTask.setBounds(302, 10, 112, 50);
		newTask.setText("New Task");
		
		Label vDivider = new Label(topPane, SWT.SEPARATOR | SWT.VERTICAL);
		vDivider.setBounds(292, 0, 4, 135);
		
		pauseResume = new Button(topPane, 0);		
		pauseResume.setBounds(302, 85, 112, 50);
		pauseResume.setText("Resume");
						
		//bottomPane -------------------------------------------------------
		bottomPane = new TabFolder(frame, SWT.BORDER);
		bottomPane.setBounds(5, 156, 415, 216);
		
		//Notes Tab -------------------------------------------------------
		tab1 = new TabItem(bottomPane, 0);
		tab1.setText("Description");	
		final Composite contentsTab1 = new Composite(bottomPane, 0);
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
		tab2 = new TabItem(bottomPane, 0);
		tab2.setText("Manage Tasks");	
		Composite contentsTab2 = new Composite(bottomPane, 0);
		tab2.setControl(contentsTab2);
		
		editNotes = new Button(contentsTab2, 0);
		editNotes.setBounds(295, 23, 100, 30);
		editNotes.setText("Edit Notes");
		
		editTime = new Button(contentsTab2, 0);
		editTime.setBounds(295, 77, 100, 30);
		editTime.setText("Edit Time");
		
		deleteTask = new Button(contentsTab2, 0);
		deleteTask.setBounds(295, 131, 100, 30);
		deleteTask.setText("Delete Task");
				
		ScrolledComposite scrolledComposite = new ScrolledComposite(contentsTab2, SWT.BORDER | SWT.V_SCROLL);
		scrolledComposite.setBounds(0, 0, 279, 183);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		allTasks = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		allTasks.setHeaderVisible(true);
		allTasks.setLinesVisible(true);
		
		// row#
		column0 = new TableColumn(allTasks, 0);
		column0.setWidth(30);
		column0.setText("#");
		
		// title
		column1 = new TableColumn(allTasks, 0);
		column1.setWidth(130);
		column1.setText("Title");
		
		// elapsed
		column2 = new TableColumn(allTasks, 0);
		column2.setWidth(68);
		column2.setText("Total");
		
		// recent
		column3 = new TableColumn(allTasks, 0);
		column3.setWidth(22);
		column3.setText("*");
		
		// taskID
		column4 = new TableColumn(allTasks, 0);
		column4.setWidth(0);
		
		// notes
		column5 = new TableColumn(allTasks, 0);
		column5.setWidth(0);
		
		// start
		column6 = new TableColumn(allTasks, 0);
		column6.setWidth(0);
		
		// end
		column7 = new TableColumn(allTasks, 0);
		column7.setWidth(0);
		
		// total
		column8 = new TableColumn(allTasks, 0);
		column8.setWidth(0);
		
		cell = new TableEditor(Main.allTasks);
						
		scrolledComposite.setContent(allTasks);
		scrolledComposite.setMinSize(allTasks.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		//Reports Tab -----------------------------------------------
		
		tab3 = new TabItem(bottomPane, 0);
		tab3.setText("Reports");	
		Composite contentsTab3 = new Composite(bottomPane, 0);
		tab3.setControl(contentsTab3);
		
		genReport = new Button(contentsTab3, 0);
		genReport.setBounds(171, 129, 108, 50);
		genReport.setText("Generate");
		
		clearReport = new Button(contentsTab3, 0);
		clearReport.setBounds(291, 129, 108, 50);
		clearReport.setText("Clear");
		
		textReport = new Text(contentsTab3, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		textReport.setLocation(10, 10);
		textReport.setSize(389, 113);
		textReport.setEnabled(false);
		
		Label sortReport = new Label(contentsTab3, 0);
		sortReport.setBounds(10, 145, 35, 15);
		sortReport.setText("Sort:");
		
		modeStart = new Button(contentsTab3, SWT.RADIO);
		modeStart.setBounds(50, 135, 90, 16);
		modeStart.setSelection(true);
		modeStart.setText("Start Time");
		
		modeEnd = new Button(contentsTab3, SWT.RADIO);
		modeEnd.setText("End Time");
		modeEnd.setBounds(50, 155, 90, 16);
		
		//Configuration Tab ----------------------------------------
		
		tab4 = new TabItem(bottomPane, 0);
		tab4.setText("Configuration");
		Composite contentsTab4 = new Composite(bottomPane, 0);
		tab4.setControl(contentsTab4);
		
		browseDialog = new Button(contentsTab4, 0);
		browseDialog.setBounds(270, 26, 108, 40);
		browseDialog.setText("Browse...");
		
		saveDialog = new Button(contentsTab4, 0);
		saveDialog.setBounds(270, 80, 108, 40);
		saveDialog.setText("Save as...");
				
		textDir = new Text(contentsTab4, SWT.BORDER);
		textDir.setEnabled(false);
		textDir.setEditable(false);
		textDir.setText("");
		textDir.setBounds(25, 58, 221, 30);
		
		fileStatus = new Label(contentsTab4, 0);
		fileStatus.setBounds(25, 140, 221, 15);
		fileStatus.setText("No file loaded");
				
		Label hDivider = new Label(frame, SWT.SEPARATOR | SWT.HORIZONTAL);
		hDivider.setBounds(5, 140, 415, 8);
		
		TaskObject.newTask();

		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////


		/**
		 * Listener Hooks
		 */

		Hooks listeners = new Hooks();
		listeners.pauseResume();
		listeners.newTask();
		listeners.collapse();
		listeners.tabListen();
		listeners.modeStart();
		listeners.modeEnd();
		listeners.genReport();
		listeners.clearReport();
		listeners.editNotes();
		listeners.editTime();
		listeners.deleteTask();
		listeners.browseDialog();
		listeners.saveDialog();
		
		TextListener textHooks = new TextListener();
		textHooks.title();
		textHooks.textNotes();
		
		TableListener tableHooks = new TableListener();
		tableHooks.recentTasks();
		tableHooks.row();
		tableHooks.column0();
		tableHooks.column1();
		tableHooks.column2();
		tableHooks.column3();
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