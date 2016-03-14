package interfUtil;

import java.util.Set;
import java.util.SortedSet;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;

public class PanneauEquipe extends Panneau{

	public PanneauEquipe()
	{
		super();
		AddListeEquipe();
	}
	
	public void AddListeEquipe()
	{
		Object[][] donnees = new Object[Inscriptions.getInscriptions().getCompetitions().size()][3];
		int cpt = 0;
		
		for (Candidat equipe : Inscriptions.getInscriptions().getCandidats()) {
			if (equipe instanceof Equipe)
			{
				donnees[cpt][0] = ((Equipe)equipe).getNom();
				donnees[cpt][1] = getCompets(equipe);
				donnees[cpt][2] = ((Equipe)equipe).getMembres();
				cpt++;
			}
			
		}
		
		String[] entetes = {"Nom", "Date de Cloture", "Nb Candidat Inscrit"};
		
		JTable tableau = new JTable(donnees, entetes);
		tableau.setDefaultRenderer(JComponent.class, new TableComponent());
		this.add(tableau);
	}
	
	private JComboBox getCompets(Candidat equipe)
	{
		Set<Competition> compets = equipe.getCompetitions();
		String[] lesCompets = new String[compets.size()];
		int cpt = 0;
		
		for (Competition compet : compets) {
			lesCompets[cpt] = compet.getNom(); 
			cpt++;
		}
		
		return new JComboBox(lesCompets);
	}
}
