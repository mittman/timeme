import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Main2 
{
	  protected static Shell shell;
	  protected static Display display;

	  private static void createWindow()
	  {
		    shell.pack();
		    shell.open();
		    shell.layout();
		    
		    while (!shell.isDisposed()) 
		    {
			      if (!display.readAndDispatch()) 
			      {
				        display.sleep();
			      }
		    }
	  }
	  
	  public static void main(String[] args) 
	  {
	    	
		    display = Display.getDefault();
		    shell = new Shell();
		    shell.setText("TimeMe");
		    
	        FrontEnd2 newContentPane = new FrontEnd2();
	        newContentPane.open();

		    createWindow();  
	  }
}