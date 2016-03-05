package menu.MenusPers;

import inscriptions.Candidat;
import inscriptions.Inscriptions;
import inscriptions.Personne;

import java.util.SortedSet;

import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuSupprimPersonne {

public static Menu RecupMenuSupprimPersonne() {
		
		Menu menu = new Menu("Supprimer une personnes:");
		SortedSet<Candidat> mesCandidats = Inscriptions.getInscriptions().getCandidats();
		int cpt = 1;
		
		for (Candidat unCandidat : mesCandidats) {
			if (unCandidat instanceof Personne) {
				menu.ajoute(getOptionSupprimer(unCandidat, cpt));
				cpt++;
			}
		}

		menu.setRetourAuto(true);
		menu.ajouteRevenir("r");
		menu.ajouteQuitter("q");
		return menu;
	}
	
	static Option getOptionSupprimer(Candidat unCandidat, int cpt) {
		return new Option("Supprimer "+ ((Personne)unCandidat).getNom(), Integer.toString(cpt) , getActionSupprimer(unCandidat));
	}
	
	static Action getActionSupprimer(Candidat unCandidat) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				unCandidat.delete();
			}
		};
	}
}
