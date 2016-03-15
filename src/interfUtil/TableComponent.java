package interfUtil;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableComponent extends DefaultTableCellRenderer{

	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		    if (value instanceof JButton)
		      return (JButton) value;
		    else if (value instanceof JComboBox)
		    	return (JComboBox<String>) value;
		    else
		      return this;
	  }
	  
}
