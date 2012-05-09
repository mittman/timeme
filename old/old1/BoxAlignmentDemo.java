import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


 
public class BoxAlignmentDemo extends JPanel {
    public BoxAlignmentDemo() {
        super(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
         
        JPanel tab1 = new JPanel();
        tab1.add(eventTab());
        tab1.add(eventTab());
    	tabbedPane.addTab("<html><body topmargin=8 marginheight=10>Add Event</body></html>", tab1);
        
        JPanel tab2 = new JPanel();
        tab2.add(eventTab());
        tab2.add(eventTab());
    	tabbedPane.addTab("<html><body topmargin=8 marginheight=10>Manage Events</body></html>", tab2);
  
        JPanel tab3 = new JPanel();
        tab3.add(eventTab());
    	tabbedPane.addTab("<html><body topmargin=8 marginheight=10>Summary</body></html>", tab3);
        
        
        JPanel bar = new JPanel();
        bar.add(header());
        
        //Add tabbedPane to this panel.
        add(bar, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.SOUTH);
    }
 
    protected JPanel header()
    {
        JPanel head = new JPanel();
        JLabel timer = new JLabel("00:00:00");
        timer.setFont(new Font(timer.getFont().getName(),timer.getFont().getStyle(),30));
        head.add(timer);
        return head;
    }
    
    protected JPanel eventTab()
    {
        JPanel pane = new JPanel();
        
 
        JComponent component = new JPanel();
        Dimension size = new Dimension(150,100);
        component.setMaximumSize(size);
        component.setPreferredSize(size);
        component.setMinimumSize(size);
        TitledBorder border = new TitledBorder(
                                  new LineBorder(Color.black),
                                  "A box here",
                                  TitledBorder.CENTER,
                                  TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.red);
        component.setBorder(border);
 
        JButton button = new JButton("This is a JButton");
        
        String title;

        title = "Matched";
        button.setAlignmentX(CENTER_ALIGNMENT);

 
        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(button);
        pane.add(component);
        return pane;
    }
      
}
