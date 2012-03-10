import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class manageTab 
{
	protected JPanel manageTab()
    {
        JPanel pane = new JPanel();
    	              
        Object rowData[][] = { { "history0", "an activity", "00:00:00" }, 
        		{ "history1", "an activity", "00:00:00" }, { "history2", "an activity", "00:00:00" }, 
        		{ "history3", "an activity", "00:00:00" }, { "history4", "an activity", "00:00:00" }, 
        		{ "history5", "an activity", "00:00:00" }, { "history6", "an activity", "00:00:00" }, 
        		{ "history7", "an activity", "00:00:00" }, { "history8", "an activity", "00:00:00" }, 
        		{ "history9", "an activity", "00:00:00" }, { "history10", "an activity", "00:00:00" }, 
        		{ "history11", "an activity", "00:00:00" }, { "history12", "an activity", "00:00:00" }, 
        		{ "history13", "an activity", "00:00:00" }, { "history14", "an activity", "00:00:00" }, 
        		{ "history15", "an activity", "00:00:00" }, { "history16", "an activity", "00:00:00" }, 
        		{ "history17", "an activity", "00:00:00" }, { "history18", "an activity", "00:00:00" }, 
        		{ "history19", "an activity", "00:00:00" }, { "history20", "an activity", "00:00:00" } };
        String columnNames[] = { "Title", "Description", "Elapsed" };

        JTable table = new JTable(rowData, columnNames);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        
        pane.add(table);
        return pane;

    }
}
