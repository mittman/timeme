/**
 * @name Main
 * @author Team 0x00000001
 */

import javax.swing.JFrame;


public class Main
{
	
	public static void main(String[] args) 
	{		
        JFrame frame = new JFrame("TimeMe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        FrontEnd newContentPane = new FrontEnd();
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);	    
	}

}
