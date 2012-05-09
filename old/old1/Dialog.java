import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Dialog 
{
    Display display = new Display();
    Shell shell = new Shell(display);
    Label label = new Label(shell, SWT.BORDER | SWT.WRAP);

    
    FileDialog fileDialog = new FileDialog(shell, SWT.MULTI);
    DirectoryDialog directoryDialog = new DirectoryDialog(shell);
    
    String sb = new String("");
    Button browse = new Button(shell, SWT.PUSH);
    Button reset = new Button(shell, SWT.PUSH);
    String selectedDir;

    public Dialog() {

        shell.setSize(200, 200);
        label.setBackground(display.getSystemColor(SWT.COLOR_RED));
        label.setText("Select a dir/file by clicking the buttons below.");
        browse.setText("Browse");

        browse.addListener(SWT.Selection, new Listener() 
        {
            public void handleEvent(Event event) 
            {

                directoryDialog.setFilterPath(selectedDir);
                directoryDialog.setMessage("Please select a directory and click OK");

                String dir = directoryDialog.open();
                if (dir != null) 
                {
                    selectedDir = dir;
                    int decide = 0;
                    System.out.println(sb);
                    String[] str = sb.split("\n");

                    for (int i = 0; i < str.length; i++) 
                    {
                        if (dir.equals(str[i])) 
                        {
                            System.out.println("dup");
                            decide = 1;
                            break;
                        }
                    }
                    if (decide == 0) 
                    {
                        sb = sb.concat(dir + "\n");
                        label.setText(sb);
                    }

                }
            }
        });


        reset.setText("Reset");
        reset.addListener(SWT.Selection, new Listener() 
        {

            @Override
            public void handleEvent(Event arg0) {
                fileDialog.setFileName(null);
                System.out.println(fileDialog.getFileName());
                sb = "";
                shell.update();
                label.setText(sb.toString());
            }

        });

        label.setBounds(0, 0, 200, 180);
        browse.setBounds(0, 185, 100, 40);
        reset.setBounds(100, 185, 100, 40);
        shell.pack();
        shell.open();

        // Set up the event loop.
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) 
            {
                // If no more entries in event queue
                display.sleep();
            }
        }

        display.dispose();
    }

    public static void main(String[] args) 
    {
        new Dialog();
    }
}