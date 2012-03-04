import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

/**
 * @author Team 0x00000001
 *
 */

public class GUI extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	public GUI()
	{
		super("Time Me");		
	    setLayout(new GridLayout(0, 2));
	    
	    JLabel msg1 = new JLabel("So  how are  you");
		add(msg1);
	    JLabel msg2 = new JLabel("doing?");
		add(msg2);
	    JLabel msg3 = new JLabel("Because  I'm  a");
		add(msg3);
	    JLabel msg4 = new JLabel("POTATO");
		add(msg4);

	    JButton button1 = new JButton("Start");
	    button1.setMnemonic(KeyEvent.VK_S);
	    button1.setToolTipText("Hint: ALT+S");
	    add(button1);

	    JButton button2 = new JButton("Pause");
	    button2.setMnemonic(KeyEvent.VK_P);
	    button2.setToolTipText("Hint: ALT+P");
	    add(button2);
	    		
	}

}
