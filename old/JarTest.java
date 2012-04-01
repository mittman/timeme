import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
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
		
		TabItem tbtmReports = new TabItem(tabFolder, SWT.NONE);
		tbtmReports.setText("Reports");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmReports.setControl(composite_3);

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