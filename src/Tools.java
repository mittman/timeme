/**
 * @name Tools
 * @author Team 0x00000001
 */

public class Tools 
{
	
	// Print debug output to console
	public static void debug(String output)
	{
		System.out.println(output);
		Main.textReport.setText(Main.textReport.getText() + "\n" + output);
	}
}
