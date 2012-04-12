import javax.swing.JFrame;

/**
 * @author Team 0x00000001
 *
 */

public class oldMain
{
	
	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
		
		GUI home = new GUI();
	    home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    home.setSize(250, 300);
	    home.setLocation(100, 100);
	    home.setVisible(true);
	    
		/*
		TabTest home = new TabTest();
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    home.setSize(250, 300);
	    home.setLocation(100, 100);
	    home.setVisible(true);
	    */

	}

}
