import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;


public class browsePath 
{
	File selected = null;
	File curDir = null;
	
	protected browsePath(Component pane, File oldDir) throws IOException
	{
		
	    JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		if (oldDir == null)
		{
			curDir = new File(System.getProperty("user.dir"));
			fileChooser.setCurrentDirectory(curDir);
		}
		else
		{
			fileChooser.setCurrentDirectory(oldDir);
		}
				
		int browse = fileChooser.showOpenDialog(pane);
		if (browse == JFileChooser.APPROVE_OPTION) 
		{
			selected = fileChooser.getSelectedFile();
			System.out.println(selected);
			loadFile readData = new loadFile(selected);
		}
	}

	protected File getSelected()
	{
		return selected;
	}
	
	protected File getCurrent()
	{
		return curDir;
	}
	
}
