import javax.swing.JFrame;

/**
 * @author kevin
 *
 */

public class Main
{
	
	public static void main(String[] args) 
	{
		System.out.println("Hello World!");
		
		GUI home = new GUI();
	    home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    home.setSize(300, 300);
	    home.setLocation(100, 100);
	    home.setVisible(true);
	}

}
