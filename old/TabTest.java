import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

/**
 * 
 */

/**
 * @author Team 00000001
 * 				
 *
 */

public class TabTest extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	JTabbedPane jtp;
	public TabTest()
	{
			super("JTabbedPane");
		    Container contents = getContentPane();
		    
		    jtp=new JTabbedPane();
		    jtp.addTab("Tab1",new JLabel("This is Tab One"));
		    jtp.addTab("Tab2",new JButton("This is Tab Two"));
		    jtp.addTab("Tab3",new JCheckBox("This is Tab Three"));
		    contents.add(jtp);
		  }
		
	}

