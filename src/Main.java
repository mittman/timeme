import javax.swing.JFrame;

/**
 * @author Team 0x00000001
 *
 */

public class Main
{
	
	public static void main(String[] args) 
	{		
        JFrame frame = new JFrame("TimeMe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        Frontend newContentPane = new Frontend();
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);	    
	}

}
