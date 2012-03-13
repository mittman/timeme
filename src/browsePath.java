import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;


public class browsePath 
{
	protected browsePath(Component pane) throws IOException
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int browse = fileChooser.showOpenDialog(pane);
		if (browse == JFileChooser.APPROVE_OPTION) 
		{
			File selected = fileChooser.getSelectedFile();
			System.out.println(selected);
			loadFile readData = new loadFile(selected);
		}
	}
}
