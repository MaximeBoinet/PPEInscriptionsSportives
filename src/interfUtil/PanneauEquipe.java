package interfUtil;

import java.awt.Component;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class PanneauEquipe extends JPanel{

	public PanneauEquipe()
	{
		AddListeEquipe();
	}
	
	public void AddListeEquipe()
	{
		int taille = 0 ;
		for (Candidat candidat : Inscriptions.getInscriptions().getCandidats()) {
			if (candidat instanceof Equipe)
				taille ++;
		}
		Object[][] donnees = new Object[taille][3];
		int cpt = 0;
		
		for (Candidat equipe : Inscriptions.getInscriptions().getCandidats()) {
			if (equipe instanceof Equipe)
			{
				donnees[cpt][0] = ((Equipe)equipe).getNom();
				donnees[cpt][1] = getCompets(equipe);
				cpt++;
			}
			
		}
		
		String[] entetes = {"Nom", "Equipe", "Membre"};
		
		JTable tableau = new JTable(donnees, entetes);
		tableau.setModel(new ZModel(donnees, entetes));
		tableau.setDefaultRenderer(JComponent.class, new TableComponent());
		tableau.getColumn("Membre").setCellRenderer(new ComboRendererEquipe());
		this.add(new JScrollPane(tableau));
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
		
		return new JComboBox<Object>(lesCompets);
	}
	
	public class ComboRendererEquipe extends JComboBox implements TableCellRenderer {

		  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocus, int row, int col) {
			SortedSet<Candidat> mesEquipes = new TreeSet<>();
			//Récupération de toute les équipe
			for (Candidat monCandidat : Inscriptions.getInscriptions().getCandidats())
				if (monCandidat instanceof Equipe)
					mesEquipes.add(monCandidat);
			
			Candidat[]  meinEquipe = new Candidat[mesEquipes.size()];
			int cpt=0;
			
			//Passage des equipes du treeset dans un tableau
			for (Candidat candidat : mesEquipes) {
				meinEquipe[cpt] = candidat;
				cpt++;
			}
			
			SortedSet<Personne> personnes = new TreeSet<>();
			int cpt2 = 0;
			
			//Passage des membre de l'quipe actuell (selon la row) dans un treeset
			for (Candidat candidat : meinEquipe) {
				if (cpt2 == row) {
					SortedSet<Personne> lespers = ((Equipe)candidat).getMembres();
					for (Personne personne : lespers) {
						personnes.add(personne);
						System.out.println("bien été ajouté");
					}
				}
				cpt2++;
			}

			//ajout des personnes dans la combobox
			for (Personne personne : personnes)
				this.addItem(personne.getNom());
			
		    return this;
		  }
		}
}
