package interfUtil;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ButtonEditor extends DefaultCellEditor {


	  protected JButton button;
	  private boolean   isPushed;
	  private ButtonListener bListener = new ButtonListener();


	  public ButtonEditor(JCheckBox checkBox) {
	    super(checkBox);
	    button = new JButton();
	    button.setOpaque(true);
	    button.addActionListener((ActionListener) bListener);
	  }


	  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) { 
	    bListener.setRow(row);
	    bListener.setColumn(column);
	    bListener.setTable(table);
	    button.setText( (value == null) ? "" : value.toString() );
	    return button;
	  }

	  class ButtonListener implements ActionListener{        
	    private int column, row;
	    private JTable table;
	    private int nbre = 0;
	    public void setColumn(int col){this.column = col;}
	    public void setRow(int row){this.row = row;}
	    public void setTable(JTable table){this.table = table;}

	    @Override
	    public void actionPerformed(ActionEvent event) {

	      //On affiche un message, mais vous pourriez effectuer les traitements que vous voulez

	      System.out.println("coucou du bouton : " + ((JButton)event.getSource()).getText() + " de la row" + row);

	      //On affecte un nouveau libellé à une autre cellule de la ligne

	      //table.setValueAt("New Value " + (++nbre), this.row, (this.column -1));

	    }
	  }     
	}