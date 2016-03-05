package menu.MenusEquipe.MenuEditionEquipes;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Inscriptions;

import java.util.SortedSet;

import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuDesinscrireCompet {

	public static Menu RecupMenuDesinscrireCompet(Candidat unCandidat) {
		Menu menu = new Menu("Desincrire "+unCandidat.getNom()+" à une compétition");
		
		SortedSet<Competition> mesCompets = Inscriptions.getInscriptions().getCompetitions();
		int cpt = 1;
		for (Competition uneCompet : mesCompets) {
			if (unCandidat.getCompetitions().contains(uneCompet)){
				menu.ajoute(getOptionDesincrire(unCandidat, uneCompet, cpt));
				cpt++;
			}
		}
		menu.setRetourAuto(true);
		menu.ajouteRevenir("r");
		menu.ajouteQuitter("q");
		return menu;
	}
	
	static Option getOptionDesincrire(Candidat unCandidat, Competition uneCompet, int cpt) {
		return new Option("Desincrire de la compétition "+uneCompet.getNom(), Integer.toString(cpt), getActionDesincrire(unCandidat, uneCompet));
	}
	
	static Action getActionDesincrire(Candidat unCandidat, Competition uneCompet) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				uneCompet.remove(unCandidat);
			}
		};
	}
}
