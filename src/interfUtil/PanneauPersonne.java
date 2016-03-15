package interfUtil;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanneauPersonne extends JPanel{

	public PanneauPersonne()
	{
		AddListePersonne();
	}
	
	public void AddListePersonne()
	{
		int taille = 0 ;
		for (Candidat candidat : Inscriptions.getInscriptions().getCandidats()) {
			if (candidat instanceof Personne)
				taille ++;
		}
		
		Object[][] donnees = new Object[taille][3];
		int cpt = 0;
		
		for (Candidat candidat : Inscriptions.getInscriptions().getCandidats()) {
			if (candidat instanceof Personne)
			{
				donnees[cpt][0] = candidat.getNom();
				donnees[cpt][1] = ((Personne) candidat).getPrenom();
				donnees[cpt][2] = ((Personne) candidat).getMail();
				cpt++;
			}
		}
		
		String[] entetes = {"Nom", "Prenom", "Mail"};
		
		JTable tableau = new JTable(donnees, entetes);
		tableau.setDefaultRenderer(JComponent.class, new TableComponent());
		this.add(new JScrollPane(tableau));
	}
}
