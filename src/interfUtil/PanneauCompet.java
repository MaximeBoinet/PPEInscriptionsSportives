package interfUtil;

import javax.swing.JComponent;
import javax.swing.JTable;

import inscriptions.Competition;
import inscriptions.Inscriptions;

public class PanneauCompet extends Panneau{
	
	public PanneauCompet()
	{
		super();
		System.out.println("construct ok");
		AddListeCompet();
		System.out.println("functok");
	}
	
	public void AddListeCompet()
	{
		System.out.println("0.5");
		Object[][] donnees = new Object[Inscriptions.getInscriptions().getCompetitions().size()][3];
		int cpt = 0;
		System.out.println(("1"));
		for (Competition compet : Inscriptions.getInscriptions().getCompetitions()) {
			donnees[cpt][0] = compet.getNom();
			donnees[cpt][1] = compet.getDateCloture();
			donnees[cpt][2] = compet.getCandidats().size();
			cpt++;
		}
		System.out.println(("2"));
		String[] entetes = {"Nom", "Date de Cloture", "Nb Candidat Inscrit"};
		
		JTable tableau = new JTable(donnees, entetes);
		tableau.setDefaultRenderer(JComponent.class, new TableComponent());
		this.add(tableau);
	}

}