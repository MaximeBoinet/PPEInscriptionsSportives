package menu.MenusCompets;

import inscriptions.Competition;
import inscriptions.Inscriptions;

import java.util.SortedSet;

import utilitaires.ligneDeCommande.Action;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Option;

public class MenuSupprimCompet {

	public static Menu RecupMenuSupprimCompet() {
		Menu menu = new Menu("Supprimer une competition:");
		SortedSet<Competition> mesCompets = Inscriptions.getInscriptions().getCompetitions();
		int cpt = 1;
		
		for (Competition uneCompet : mesCompets) {
			menu.ajoute(getOptionSupprimer(uneCompet, cpt));
			cpt++;
		}

		menu.setRetourAuto(true);
		menu.ajouteRevenir("r");
		menu.ajouteQuitter("q");
		return menu;
	}
	
	static Option getOptionSupprimer(Competition uneCompet, int cpt) {
		return new Option ("Supprimer la compétition " + uneCompet.getNom(), Integer.toString(cpt), getActionSupprimer(uneCompet));
	}
	
	static Action getActionSupprimer(Competition uneCompet) {
		return new Action() {
			@Override
			public void optionSelectionnee() {
				uneCompet.delete();
			}
		};
	}
}
