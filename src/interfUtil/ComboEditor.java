package interfUtil;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Inscriptions;

public class ComboEditor extends DefaultCellEditor{
	
	JComboBox jcomb;
	private ComboListener cListener = new ComboListener();
	public ComboEditor(JComboBox jcombo) {
	    super(jcombo);
	    jcomb = new JComboBox();
	    jcomb.setOpaque(true);
	    jcomb.addActionListener((ActionListener) cListener);
	  }

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) { 
		cListener.setRow(row);
		cListener.setColumn(column);
		cListener.setTable(table);
		Competition[]  competition = new Competition[Inscriptions.getInscriptions().getCandidats().size()];
		competition = Inscriptions.getInscriptions().getCandidats().toArray(competition);
		for (Candidat candidat : competition[row].getCandidats()) {
			jcomb.addItem(candidat.getNom());
		}
	    return jcomb;
	 }
	
	class ComboListener implements ActionListener
	{
		private int column, row;
	    private JTable table;
	    private int nbre = 0;
	    public void setColumn(int col){this.column = col;}
	    public void setRow(int row){this.row = row;}
	    public void setTable(JTable table){this.table = table;}
		@Override
		public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		}
	}

	
}
