import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class clockPane 
{
    protected JPanel clockPane()
    {
        JPanel head = new JPanel();
        JLabel timer = new JLabel("00:00:00");  
        timer.setFont(new Font(timer.getFont().getName(),timer.getFont().getStyle(),50));
        timer.setAlignmentX(0);
        head.setLayout(new BoxLayout(head, BoxLayout.Y_AXIS));
        
        JPanel dock = new JPanel();
        JButton button1 = new JButton("<html><body topmargin=8 marginheight=10>RESUME</body></html>");
        button1.setAlignmentX(0);
        JButton button2 = new JButton("<html><body topmargin=8 marginheight=10>PAUSE</body></html>");
        button2.setAlignmentX(0); 
        dock.setLayout(new BoxLayout(dock, BoxLayout.X_AXIS));
        
        head.add(timer);
        dock.add(button1);
        dock.add(button2);
        head.add(dock);
        
 
        return head;
    }
}
