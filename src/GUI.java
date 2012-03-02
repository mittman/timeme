import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

/**
 * @author kevin
 *
 */

public class GUI extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	public GUI()
	{
		super("Time Me");		
	    setLayout(new GridLayout(0, 2));
	    
	    JLabel msg1 = new JLabel("How are you doing?");
		add(msg1);
	    JLabel msg2 = new JLabel("Because I'm a POTATO");
		add(msg2);
		JLabel spacer2 = new JLabel("");
		add(spacer2);
		JLabel spacer3 = new JLabel("");
		add(spacer3);

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
