package interfUtil;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

class ZModel extends AbstractTableModel{
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
    
  //Retourne vrai si la cellule est éditable : celle-ci sera donc éditable
    public boolean isCellEditable(int row, int col){
      //On appelle la méthode getValueAt qui retourne la valeur d'une cellule
      //Et on effectue un traitement spécifique si c'est un JButton
      if(getValueAt(0, col) instanceof JButton)
        return false;
      return true; 
    }
}