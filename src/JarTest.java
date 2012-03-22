import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;


public class JarTest {

	protected Shell shell;
	private Text txtSubject;
	private Text txtNotes;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JarTest window = new JarTest();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 400);
		shell.setText("SWT Application");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 434, 129);
		
		final Button button = new Button(composite, SWT.PUSH);
		button.setText("<<");
		button.setBounds(319, 104, 107, 25);
		
		List list = new List(composite, SWT.BORDER);
		list.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		list.setItems(new String[] {"Recent Item 1", "Recent Item 2", "Recent Item 3", "Recent Item 4", "Recent Item 5"});
		list.setBounds(161, 0, 152, 129);
		
		Button btnNewTask = new Button(composite, SWT.NONE);
		btnNewTask.setBounds(5, 5, 150, 58);
		btnNewTask.setText("New Task");
		
		Button btnPauseresume = new Button(composite, SWT.NONE);
		btnPauseresume.setBounds(5, 69, 150, 60);
		btnPauseresume.setText("Pause/Resume");
		
		Label label = new Label(composite, SWT.NONE);
		label.setAlignment(SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("Segoe UI", 32, SWT.NORMAL));
		label.setBounds(319, 20, 107, 65);
		label.setText("00:00");
		
		final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(0, 135, 434, 228);
		
		TabItem tbtmNotes = new TabItem(tabFolder, SWT.NONE);
		tbtmNotes.setText("Notes");
		
		final Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNotes.setControl(composite_1);
		
		txtSubject = new Text(composite_1, SWT.BORDER);
		txtSubject.setText("Subject");
		txtSubject.setBounds(0, 0, 76, 21);
		
		txtNotes = new Text(composite_1, SWT.BORDER);
		txtNotes.setText("Notes");
		txtNotes.setBounds(0, 27, 426, 173);
		
		TabItem tbtmManageTasks = new TabItem(tabFolder, SWT.NONE);
		tbtmManageTasks.setText("Manage Tasks");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmManageTasks.setControl(composite_2);
		
		table = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 0, 279, 200);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		Button btnEditNotes = new Button(composite_2, SWT.NONE);
		btnEditNotes.setBounds(285, 0, 141, 31);
		btnEditNotes.setText("Edit Notes");
		
		Button btnModfyRecordedT = new Button(composite_2, SWT.NONE);
		btnModfyRecordedT.setBounds(285, 37, 141, 31);
		btnModfyRecordedT.setText("Modfy Time");
		
		Button btnRemoveTask = new Button(composite_2, SWT.NONE);
		btnRemoveTask.setBounds(285, 74, 141, 31);
		btnRemoveTask.setText("Remove Task");
		
		Button btnReturnToRecent = new Button(composite_2, SWT.NONE);
		btnReturnToRecent.setBounds(285, 111, 141, 31);
		btnReturnToRecent.setText("Resume Task");
		
		Button btnIncreasePriority = new Button(composite_2, SWT.NONE);
		btnIncreasePriority.setBounds(283, 146, 68, 44);
		btnIncreasePriority.setText("^^");
		
		Button btnV = new Button(composite_2, SWT.NONE);
		btnV.setBounds(357, 146, 68, 44);
		btnV.setText("vv");
		
		TabItem tbtmReports = new TabItem(tabFolder, SWT.NONE);
		tbtmReports.setText("Reports");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmReports.setControl(composite_3);
		
		Button btnNewButton = new Button(composite_3, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.NORMAL));
		btnNewButton.setBounds(270, 126, 146, 64);
		btnNewButton.setText("Run Report");
		
		Button btnRadioButton = new Button(composite_3, SWT.RADIO);
		btnRadioButton.setBounds(10, 39, 117, 23);
		btnRadioButton.setText("Last Time Worked");
		
		Button btnRadioButton_1 = new Button(composite_3, SWT.RADIO);
		btnRadioButton_1.setBounds(10, 68, 117, 16);
		btnRadioButton_1.setText("First Time Worked");
		
		Label lblRunReportBased = new Label(composite_3, SWT.NONE);
		lblRunReportBased.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.NORMAL));
		lblRunReportBased.setBounds(10, 10, 176, 23);
		lblRunReportBased.setText("Run Report Based On:");
		
		Button btnRadioButton_2 = new Button(composite_3, SWT.RADIO);
		btnRadioButton_2.setBounds(10, 90, 117, 23);
		btnRadioButton_2.setText("Radio Button");

		button.addSelectionListener(new SelectionAdapter() {
			
			boolean interfaceDown = true;
			final Rectangle up = new Rectangle(0,0,0,0);
			final Rectangle down = new Rectangle(0, 135, 434, 228);

			
			public void widgetSelected(SelectionEvent e) {
				if (interfaceDown == true) {
					tabFolder.setBounds(up);
					button.setText(">>");
					interfaceDown = false;
				} else {
					tabFolder.setBounds (down);
					button.setText("<<");
					//txtSubject.setBounds(0, 0, 76, 21);
					//txtNotes.setBounds(0, 27, 434, 155);
					interfaceDown = true;
				}
				shell.pack ();
			}
		});
	}
}
