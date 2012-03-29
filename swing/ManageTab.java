/**
 * @name ManageTab
 * @author Team 0x00000001
 */

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ManageTab 
{
	protected JPanel ManageTab()
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

        final JTable table = new JTable(rowData, columnNames);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        
        
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() 
        {
        	int oldNum = -1;
            public void valueChanged(ListSelectionEvent e) 
            {
              String selectedData = null;
              int num = -1;
              int[] selectedRow = table.getSelectedRows();
              
              for (int i = 0; i < selectedRow.length; i++) 
              {
            	  num = table.getSelectedRow();
            	  selectedData = (String) table.getValueAt(selectedRow[i], 0);
              }
              
              if (num != oldNum)
              {
            	  System.out.println("Selected: " + num + " " + selectedData);
              }
              oldNum = num;
            }

          });
        
        
        pane.add(table);
        return pane;

    }
}
