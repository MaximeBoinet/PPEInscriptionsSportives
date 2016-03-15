package interfUtil;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import inscriptions.Competition;
import inscriptions.Inscriptions;

@SuppressWarnings("serial")
public class PanneauCompet extends JPanel{
	
	public PanneauCompet()
	{
		AddListeCompet();
	}
	
	public void AddListeCompet()
	{
		Object[][] donnees = new Object[Inscriptions.getInscriptions().getCompetitions().size()][3];
		int cpt = 0;
		for (Competition compet : Inscriptions.getInscriptions().getCompetitions()) {
			donnees[cpt][0] = compet.getNom();
			donnees[cpt][1] = compet.getDateCloture();
			donnees[cpt][2] = compet.getCandidats().size();
			cpt++;
		}
		String[] entetes = {"Nom", "Date de Cloture", "Nb Candidat Inscrit"};
		
		JTable tableau = new JTable(donnees, entetes);
		tableau.setModel(new ZModel(donnees, entetes));
		tableau.setDefaultRenderer(JComponent.class, new TableComponent());
		this.add(new JScrollPane(tableau));
	}
	
	public class ComboRenderer extends JComboBox implements TableCellRenderer {

		  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocus, int row, int col) {
		    this.addItem("Très bien");
		    this.addItem("Bien");
		    this.addItem("Mal");
		    return this;
		  }
		}
}
