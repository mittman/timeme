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


 
public class Prototype1 extends JPanel {
    public Prototype1() {
        super(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
         
        JPanel tab1 = new JPanel();
        tab1.add(eventTab());
        tab1.add(eventDock());
    	tabbedPane.addTab("<html><body topmargin=8 marginheight=10>Add Event</body></html>", tab1);
        
        JPanel tab2 = new JPanel();
        tab2.add(manageTab());
        tab2.add(manageDock());
    	tabbedPane.addTab("<html><body topmargin=8 marginheight=10>Manage Events</body></html>", tab2);
  
        JPanel tab3 = new JPanel();
        tab3.add(summaryTab());
        tab3.add(summaryDock());
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
    
    protected JPanel eventDock()
    {
        JPanel pane = new JPanel();
        
        JComponent buttons = new JPanel();
        Dimension dock = new Dimension(200,250);
        buttons.setMaximumSize(dock);
        buttons.setPreferredSize(dock);
        buttons.setMinimumSize(dock);
        
        JButton button1 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>START</body></html>");
        button1.setAlignmentX(CENTER_ALIGNMENT);
        JButton button2 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>STOP</body></html>");
        button2.setAlignmentX(CENTER_ALIGNMENT);
        buttons.add(button1);
        buttons.add(button2);
                
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.add(buttons);
        return pane;
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

        title = "Add New";
        button.setAlignmentX(CENTER_ALIGNMENT);

 
        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(button);
        pane.add(component);
        return pane;
    }
    
    protected JPanel manageDock()
    {
        JPanel pane = new JPanel();
        
        JComponent buttons = new JPanel();
        Dimension dock = new Dimension(200,250);
        buttons.setMaximumSize(dock);
        buttons.setPreferredSize(dock);
        buttons.setMinimumSize(dock);
        
        JButton button1 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>RESUME</body></html>");
        button1.setAlignmentX(CENTER_ALIGNMENT);
        JButton button2 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>EDIT</body></html>");
        button2.setAlignmentX(CENTER_ALIGNMENT);
        JButton button3 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>DELETE</body></html>");
        button3.setAlignmentX(CENTER_ALIGNMENT);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
                

        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.add(buttons);
        return pane;
    }
      
    protected JPanel manageTab()
    {
        JPanel pane = new JPanel();
    	
        JComponent component = new JPanel();
        Dimension size = new Dimension(100,100);
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
         
        String title;
        title = "Manage";

        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.add(component);
        return pane;

    }

    protected JPanel summaryDock()
    {
        JPanel pane = new JPanel();
        
        JComponent buttons = new JPanel();
        Dimension dock = new Dimension(200,250);
        buttons.setMaximumSize(dock);
        buttons.setPreferredSize(dock);
        buttons.setMinimumSize(dock);
        
        JButton button1 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>BROWSE</body></html>");
        button1.setAlignmentX(CENTER_ALIGNMENT);
        JButton button2 = new JButton("<html><body leftmargin=20 topmargin=8 marginwidth=30 marginheight=20>GENERATE</body></html>");
        button2.setAlignmentX(CENTER_ALIGNMENT);
        buttons.add(button1);
        buttons.add(button2);
                
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.add(buttons);
        return pane;
    }
    
    protected JPanel summaryTab()
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

        title = "Summary";
        button.setAlignmentX(CENTER_ALIGNMENT);

 
        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(button);
        pane.add(component);
        return pane;
    }
}
