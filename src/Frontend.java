import java.awt.BorderLayout;
import javax.swing.JPanel;
 
public class Frontend extends JPanel 
{
    public Frontend() 
    {
    	super(new BorderLayout());
        add(new topPane().topPane(), BorderLayout.NORTH);
        add(new bottomPane().bottomPane(), BorderLayout.SOUTH);
    }  
}
