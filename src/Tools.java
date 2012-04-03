/**
 * @name Tools
 * @project TimeMe
 * @author Team 0x00000001
 */


public class Tools 
{
	// Print debug output to console
	public static void debug(String output)
	{
		System.out.println(output);
		Main.textReport.setText(output + "\n" + Main.textReport.getText());
	}
}
