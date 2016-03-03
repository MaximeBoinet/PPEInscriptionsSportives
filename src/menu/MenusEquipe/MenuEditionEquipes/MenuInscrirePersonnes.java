package menu.MenusEquipe.MenuEditionEquipes;

import inscriptions.Candidat;
import inscriptions.Inscriptions;
import inscriptions.Personne;
import inscriptions.Equipe;

import java.util.SortedSet;

import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuInscrirePersonnes {
	
	public static Menu RecupMenuInscrirePersonnes(Candidat unCandidat) {
		Menu menu = new Menu("Inscrire un utilisateur à l'equipe "+unCandidat.getNom());
		
		SortedSet<Candidat> mesCandidats = Inscriptions.getInscriptions().getCandidats();
		int cpt = 1;
		for (Candidat unCandidat2 : mesCandidats) {
			if (unCandidat2 instanceof Personne){
				menu.ajoute(getOptionInscrire(unCandidat, unCandidat2, cpt));
				cpt++;
			}
		}
		menu.setRetourAuto(true);
		menu.ajouteRevenir("r");
		menu.ajouteQuitter("q");
		return menu;
	}
	
	static Option getOptionInscrire(Candidat unCandidat, Candidat unCandidat2, int cpt) {
		return new Option("Inscrire"+unCandidat2.getNom()+ "à l'équipe", Integer.toString(cpt), getActionInscrire(unCandidat, unCandidat2));
	}
	
	static Action getActionInscrire(Candidat unCandidat, Candidat unCandidat2) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				((Equipe)unCandidat).add((Personne)unCandidat2);
			}
		};
	}
}
