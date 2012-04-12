/**
 * @name FrontEnd
 * @author Team 0x00000001
 */

import java.awt.BorderLayout;
import javax.swing.JPanel;


public class FrontEnd extends JPanel 
{
    public FrontEnd() 
    {
    	super(new BorderLayout());
        add(new TopPane().TopPane(), BorderLayout.NORTH);
        add(new BottomPane().BottomPane(), BorderLayout.SOUTH);
    }  
}
