package interfUtil;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

public class ZModel extends AbstractTableModel{

    private Object[][] data;
    private String[] title;

    //Constructeur
    public ZModel(Object[][] data, String[] title){
      this.data = data;
      this.title = title;
    }

    //Retourne le nombre de colonnes
    public int getColumnCount() {
      return this.title.length;
    }

    //Retourne le nombre de lignes
    public int getRowCount() {
      return this.data.length;
    }

    //Retourne la valeur à l'emplacement spécifié
    public Object getValueAt(int row, int col) {
      return this.data[row][col];
    }    
    
    public String getColumnName(int col) {
    	  return this.title[col];
    }
    
    public boolean isCellEditable(int row, int col){
    	if(getValueAt(0, col) instanceof JButton)
    		return false;
    	else if(getValueAt(0,col) instanceof JComboBox)
    		return false;
    	return true; 
    }
    
    public Class getColumnClass(int col){
    	  return this.data[0][col].getClass();
    }
}
