package interfUtil;

import inscriptions.Competition;
import inscriptions.Inscriptions;

import javax.swing.JComponent;
import javax.swing.JTable;

public class PanneauPersonne extends Panneau{

	public PanneauPersonne()
	{
		super();
		AddListePersonne();
	}
	
	public void AddListePersonne()
	{
		Object[][] donnees = new Object[Inscriptions.getInscriptions().getCompetitions().size()][3];
		int cpt = 0;
		
		for (Competition compet : Inscriptions.getInscriptions().getCompetitions()) {
			donnees[cpt][0] = compet.getNom();
			donnees[cpt][1] = compet.getDateCloture();
			donnees[cpt][2] = compet.getCandidats().size();
		}
		
		String[] entetes = {"Nom", "Date de Cloture", "Nb Candidat Inscrit"};
		
		JTable tableau = new JTable(donnees, entetes);
		tableau.setDefaultRenderer(JComponent.class, new TableComponent());
		this.add(tableau);
	}
}