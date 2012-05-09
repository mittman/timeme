/**
 * @name FrontEnd
 * @author Team 0x00000001
 */



import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.TabFolder;


public class FrontEnd2
{
    public void open() 
    {
    	/*
    	super(new BorderLayout());
        add(new TopPane().TopPane(), BorderLayout.NORTH);
        add(new BottomPane().BottomPane(), BorderLayout.SOUTH);
        */
        
	    Composite composite = new Composite(Main2.shell, SWT.NONE);
	    composite.setBounds(0, 0, 434, 129);
	        
	    Button button1 = new Button(composite, SWT.NONE);
	    button1.setBounds(5, 5, 150, 58);
	    button1.setText("New Task");
	    
	    final Button button2 = new Button(composite, SWT.PUSH);
	    button2.setBounds(200, 5, 150, 58);
	    button2.setText("<<");
	    
	    final TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
	    tabFolder.setBounds(0, 135, 434, 228);
	    
	    button2.addSelectionListener(new SelectionAdapter() 
	    {
	        boolean interfaceDown = true;
	        final Rectangle up = new Rectangle(0,0,0,0);
	        final Rectangle down = new Rectangle(0, 135, 434, 228);

	   	    
	      		public void widgetSelected(SelectionEvent e) 
	      		{
	    			if (interfaceDown == true) 
	    			{
	    				tabFolder.setBounds(up);
	    				button2.setText(">>");
	    				interfaceDown = false;
	    			} 
	    			else 
	    			{
	    				tabFolder.setBounds (down);
	    				button2.setText("<<");
	    				interfaceDown = true;
	    			}
	      		}
	    });

	    
    }  
}
